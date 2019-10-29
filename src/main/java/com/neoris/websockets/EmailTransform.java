package com.neoris.websockets;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;

import com.neoris.websockets.filetransfer.FileToTransfer;


// TODO: Auto-generated Javadoc
/**
 * The Class EmailTransform.
 * 
 * @author marco.slehiman
 */
public class EmailTransform {
	
	/** The document. */
	static Document document;

	/**
	 * Gets the email body.
	 *
	 * @param xmlDocument the xml document with Email detail Email From , EmailTO ...
	 * @param xsltDocumet the xslt documet with the email body template
	 * @return the email body 
	 */
	public String getEmailBody(Document xmlDocument, String xsltDocumet) {
		StringWriter outputWriter = new StringWriter();
		EmailTransform.class.getResourceAsStream(xsltDocumet);
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// Remove comments to validate the XML, now is not required because is no DTD nor XSD to validate it
		// factory.setNamespaceAware(true);
		// factory.setValidating(true);
		try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = xmlDocument;

            // Use a Transformer for output
            TransformerFactory tFactory = TransformerFactory.newInstance();
            StreamSource stylesource = new StreamSource(EmailTransform.class.getResourceAsStream(xsltDocumet));
            Transformer transformer = tFactory.newTransformer(stylesource);
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(outputWriter);
            transformer.transform(source, result);
		} catch (TransformerConfigurationException tce) {
			// Error generated by the parser
			System.out.println("\n** Transformer Factory error");
			System.out.println("   " + tce.getMessage());
			// Use the contained exception, if any
			Throwable x = tce;
			if (tce.getException() != null) {
				x = tce.getException();
			}
			x.printStackTrace();
		} catch (TransformerException te) {
			// Error generated by the parser
			System.out.println("\n** Transformation error");
			System.out.println("   " + te.getMessage());

			// Use the contained exception, if any
			Throwable x = te;

			if (te.getException() != null) {
				x = te.getException();
			}

			x.printStackTrace();
		} catch (ParserConfigurationException pce) {
			// Parser with specified options can't be built
			pce.printStackTrace();
		}
		return outputWriter.toString();
	} // main

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	static public void main(String args[]) throws Exception{
		EmailTransform emailTrasnform = new EmailTransform();
		FileToTransfer f = new FileToTransfer("emailFrom", "emailTo", "name", 10);
		System.out.println(emailTrasnform.getEmailBody(f.getAsDocument(), "emailToAfterUpload.xslt"));
	}
}
