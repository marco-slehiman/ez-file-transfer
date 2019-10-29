package com.neoris.websockets.filetransfer;

import java.io.Serializable;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

// TODO: Auto-generated Javadoc
/**
 * The Class FileToTransfer.
 */
public class FileToTransfer implements Serializable{
	
	/** The email from. */
	private String emailFrom;
	
	/** The email to. */
	private String emailTo;
	
	/** The name. */
	private String name;
	
	/** The size. */
	private long size;

	/**
	 * Instantiates a new file to transfer.
	 *
	 * @param emailFrom the email from
	 * @param emailTo the email to
	 * @param name the name
	 * @param size the size
	 */
	public FileToTransfer(String emailFrom, String emailTo, String name, long size){
		setEmailFrom(emailFrom);
		setEmailTo(emailTo);
		setName(name);
		setSize(size);
	}

	/**
	 * Gets the email from.
	 *
	 * @return the email from
	 */
	public String getEmailFrom() {
		return emailFrom;
	}

	/**
	 * Gets the email to.
	 *
	 * @return the email to
	 */
	public String getEmailTo() {
		return emailTo;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * Sets the email from.
	 *
	 * @param emailFrom the new email from
	 */
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	/**
	 * Sets the email to.
	 *
	 * @param emailTo the new email to
	 */
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(long size) {
		this.size = size;
	}
	
	/**
	 * Gets the as document.
	 *
	 * @return the as document
	 * @throws ParserConfigurationException the parser configuration exception
	 */
	public Document getAsDocument() throws ParserConfigurationException {
    	DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document document = docBuilder.newDocument();
		Element rootElement = document.createElement("ezFileTransfer");
		document.appendChild(rootElement);
		Element child = document.createElement("emailFrom");
		child.appendChild(document.createTextNode(this.emailFrom));
		rootElement.appendChild(child);
		child = document.createElement("emailTo");
		child.appendChild(document.createTextNode(this.emailTo));
		rootElement.appendChild(child);
		child = document.createElement("fileName");
		child.appendChild(document.createTextNode(this.name));
		rootElement.appendChild(child);
		child = document.createElement("fileSize");
		child.appendChild(document.createTextNode(String.valueOf(this.size)));
		rootElement.appendChild(child);
		return document;
    }

}
