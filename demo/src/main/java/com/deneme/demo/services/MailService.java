package com.deneme.demo.services;

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
import org.springframework.stereotype.Service;

import com.deneme.demo.entities.Alerts;
import com.deneme.demo.entities.StockModel;
import com.deneme.demo.entities.User;
import com.deneme.demo.repos.StockRepository;
import com.deneme.demo.repos.UserRepository;

@Service
public class MailService {
	
	@Autowired
	StockRepository stockRepository;
	@Autowired
	UserRepository userRepository;
	
	private final static String SYSTEMMAIL = "demoProjectServiceMail@gmail.com";
	private final static String SYSTEMPASSWORD = "deneme.Sifre123";
	
	private User user;
	private StockModel stock;
	
	public void sendMail(Alerts alert) {
		stock = stockRepository.findByStockSymbol(alert.getStockSymbol());
		user = userRepository.findById(alert.getUserId()).orElse(null);
		
		
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
		
		Message message = prepareMessage(session,SYSTEMMAIL,user.getMail(),user.getName()+ " "+ user.getSurname(),stock.getStockName(),alert.getAlertPrice(),stock.getStockSymbol());
		try {
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	private  Message prepareMessage(Session session, String systemMail, String userMail,String userName,String stockName, BigDecimal alertPrice,String stockSymbol) {
		try {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(systemMail));
		message.setRecipient(Message.RecipientType.TO,new InternetAddress(userMail));
		message.setSubject("HİSSE ALARMI");
		message.setText("Merhaba "+userName+"\nFiyat alarmı kurduğun "+ stockName +" hissesi " + alertPrice +" seviyesine geldi!\nHisseyi Görüntülemek için : http://localhost:8080/market/"+stockSymbol);
		return message;
		}
		catch(Exception e) {
		}
		return null;
		
	}
	
}
