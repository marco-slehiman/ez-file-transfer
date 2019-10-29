package com.neoris.websockets;

import com.neoris.websockets.filetransfer.FileToTransfer;
import com.neoris.websockets.filetransfer.JsonDecoder;
import com.neoris.websockets.filetransfer.Operation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

// TODO: Auto-generated Javadoc
/**
 * The Class FileTransferService.
 *
 * @author marco.slehiman
 */
@ServerEndpoint(value = "/websocket/fileTransfer", decoders = { JsonDecoder.class })
public class FileTransferService {

   /** The buffer size . */
   private int bufferSize = 4194304 + 4;

   /** The session for websocket. */
   private Session session;

   /** The length for buffer received by the browser it can be small than bufferSize. */
   private long length = 0;

   /** The storage is the path where the file and slices files are stored temporaly. */
   private File storage;

   /** The smtp session used to send the email notifications. */
   private javax.mail.Session smtpSession;

   /** The file to transfer has the propertieds of the file received. */
   private FileToTransfer fileToTransfer;

   /**
    * Close.
    *
    * @param session
    *           the session
    */
   @OnClose
   public void close(Session session) {
      System.out.println("On Close " + session.getId());
   }

   /**
    * On error.
    *
    * @param error
    *           the error
    */
   @OnError
   public void onError(Throwable error) {
      System.out.println("On Error: " + error.getMessage());
      error.printStackTrace();
   }

   /**
    * Process binary stream.
    *
    * @param is
    *           the is
    */
   @OnMessage
   public void processBinaryStream(InputStream is) {
      System.out.println("processBinaryStream");
      String prefix = new String();
      boolean writeFile = false;
      FileOutputStream fos = null;
      try {
         int byteToWrite = is.read();
         while (byteToWrite != -1) {
            if (fileToTransfer.getSize() < length) {
               break;
            }
            if (writeFile) {
               fos.write(byteToWrite);
               this.length++;
               byte[] buffer = new byte[this.bufferSize];
               int bytesLoaded = is.read(buffer);
               fos.write(buffer, 0, bytesLoaded);
               this.length += bytesLoaded;
            } else {
               if (byteToWrite == '|') {
                  fos = new FileOutputStream(new File(storage, prefix + "_" + this.fileToTransfer.getName()));
                  writeFile = true;
               } else {
                  prefix += (char) byteToWrite;
               }
            }
            byteToWrite = is.read();
         }
         fos.flush();
         fos.close();
         sendProgress();
      } catch (IOException e) {
         e.printStackTrace();
      }
      System.gc();
   }

   /**
    * Process on open.
    *
    * @param session the session
    */
   @OnOpen
   public void processOnOpen(Session session) {
      System.out.println("Session Open " + session.getId());
      this.storage = new File("D:\\uploads\\" + session.getId());
      if (!this.storage.exists()) {
         this.storage.mkdir();
      }
      this.session = session;
   }

   /**
    * Put file.
    *
    * @param operation the operation
    */
   private void putFile(Operation operation) {
      fileToTransfer = new FileToTransfer(operation.getEmailFrom(), operation.getEmailTO(), operation.getFileName(),
            operation.getFileSize());
   }

   /**
    * Process operation.
    *
    * @param operation the operation
    * @throws IOException Signals that an I/O exception has occurred.
    */
   @OnMessage
   public void processOperation(Operation operation) throws IOException {
      System.out.println(operation.toString());
      switch (operation.getOperation()) {
      case ("put"):
         putFile(operation);
         break;
      default:
         break;
      }
   }

   /**
    * Send email.
    */
   public void sendEmail() {
      EmailTransform emailTransform = new EmailTransform();
      // sendEmail(fileToTransfer.getEmailTo(), fileToTransfer.getEmailFrom() + "te ha
      // enviado archivox con ez File Transfer", emailTransform);
   }

   /**
    * Send email.
    *
    * @param to the to
    * @param subject the subject
    * @param body the body
    */
   public void sendEmail(String to, String subject, String body) {
      try {
         Context ctx = new InitialContext();
         smtpSession = (javax.mail.Session) ctx.lookup("java:comp/env/email/fileTransferSmtp");
         MimeMessage message = new MimeMessage(smtpSession);
         System.out.println();
         message.setFrom(smtpSession.getProperty("username"));
         InternetAddress[] address = InternetAddress.parse(to);
         message.setRecipients(Message.RecipientType.TO, address);
         message.setSubject(subject);
         message.setText(body);
         Transport transport = smtpSession.getTransport();
         transport.connect(smtpSession.getProperty("username"), "#R0s4l1nd4$");
         transport.sendMessage(message, message.getAllRecipients());
         transport.close();
      } catch (NamingException | MessagingException ex) {
         ex.printStackTrace();
      }
   }

   /**
    * Send progress.
    */
   private void sendProgress() {
      String progress = String.valueOf((100 * length) / fileToTransfer.getSize());
      try {
         session.getBasicRemote().sendText(String.valueOf(progress));
         if (progress.equals("100")) {
            sendEmail();
         }
      } catch (IOException e) {
         e.printStackTrace();
      }

   }

}
