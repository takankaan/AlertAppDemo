package com.project.alertapp.services;

import java.math.BigDecimal;
import java.util.Properties;


import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.project.alertapp.entities.Alerts;
import com.project.alertapp.entities.StockModel;
import com.project.alertapp.entities.User;
import com.project.alertapp.repos.StockRepository;
import com.project.alertapp.repos.UserRepository;


/**
 * This service includes methods for sending alarm e-mails to users. 
 * @author KaanSarigul
 *
 */
@Service
public class MailService {

	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Value("${mailaddress}")
	private String SYSTEMMAIL;
	@Value("${mailpassword}")
	private String SYSTEMPASSWORD;
	
	/**
	 * This method gets the data of the triggered alert and sends an e-mail to the user who needs to be alerted.
	 * @param alert This object contains required user and stock data.
	 */
	public void sendMail(Alerts alert) {
		StockModel stock = stockRepository.findByStockSymbol(alert.getStockSymbol());
		User user = userRepository.findById(alert.getUserId()).orElse(null);
		
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(properties,new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication (SYSTEMMAIL,SYSTEMPASSWORD);
			}
		});
		
		Message message = prepareMessage(session,user.getMail(),user.getName()+ " "+ user.getSurname(),stock.getStockName(),alert.getAlertPrice(),stock.getStockSymbol());
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method prepares the content of the mail to be sent. 
	 * @param session Provides login information for the mail to be sent.
	 * @param userMail Contains the recipient's address.
	 * @param userName Contains user's name and surname.
	 * @param stockName Contains the stock name to which the alarm belongs
	 * @param alertPrice Current alarm price.
	 * @param stockSymbol StockSymbol is unique key for stocks.
	 * @return Returns prepared message object.
	 */
	public  Message prepareMessage(Session session, String userMail,String userName,String stockName, BigDecimal alertPrice,String stockSymbol) {
		if(session != null && userMail != null && userName != null && stockName != null && alertPrice !=null && stockSymbol != null)
		{
			try {
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(SYSTEMMAIL));
				message.setRecipient(Message.RecipientType.TO,new InternetAddress(userMail));
				message.setSubject("HİSSE ALARMI");
				message.setText("Merhaba "+userName+"\nFiyat alarmı kurduğun "+ stockName +" hissesi " + alertPrice +" seviyesine geldi!\nHisseyi Görüntülemek için : http://localhost:8080/market/"+stockSymbol);
				return message;
			}
			catch(Exception e) {
				return null;
			}
			
		}else
			return null;
		
	}
	
}
