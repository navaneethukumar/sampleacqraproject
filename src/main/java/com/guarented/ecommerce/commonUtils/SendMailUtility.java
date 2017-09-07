package com.guarented.ecommerce.commonUtils;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import com.guarented.ecommerce.constants.GuarentedConstants;

public class SendMailUtility {
	public static void sendMail(File file) {

			final String username = GuarentedConstants.SENDER_NAME;
			final String password = GuarentedConstants.SENDER_PASSWORD;

			Properties props = new Properties();
			props.put(GuarentedConstants.PROPERTY_AUTH, GuarentedConstants.PROPERTY_AUTH_VALUE);
			props.put(GuarentedConstants.PROPERTY_STARTTLS, GuarentedConstants.PROPERTY_STARTTLS_VALUE);
			props.put(GuarentedConstants.PROPERTY_HOST, GuarentedConstants.PROPERTY_HOST_VALUE);
			props.put(GuarentedConstants.PROPERTY_port, GuarentedConstants.PROPERTY_port_VALUE);

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			// Please find automatch report as date and time
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(GuarentedConstants.MAIL_SEND_FROM));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(GuarentedConstants.MAIL_SEND_TO));
				Date date = new Date();
				message.setSubject("IDC Report on " + date.toString());

				message.setText("PFA");

				MimeBodyPart messageBodyPart = new MimeBodyPart();

				Multipart multipart = new MimeMultipart();

				messageBodyPart = new MimeBodyPart();

				String FilepathAsString = file.getAbsolutePath();
				DataSource source = new FileDataSource(FilepathAsString);
				messageBodyPart.setDataHandler(new DataHandler(source));

				messageBodyPart.setFileName(FilepathAsString);
				multipart.addBodyPart(messageBodyPart);

				message.setContent(multipart);

				System.out.println("Sending");

				Transport.send(message);

				System.out.println("Done");

			} catch (MessagingException e) {
				e.printStackTrace();

			}
		}
}
