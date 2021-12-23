package com.project.alertapp.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.project.alertapp.entities.Alerts;
import com.project.alertapp.entities.StockModel;
import com.project.alertapp.entities.User;
import com.project.alertapp.repos.StockRepository;
import com.project.alertapp.repos.UserRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class MailServiceTest {
	@InjectMocks
	private MailService mailService;
	@Mock
	private StockRepository stockRepository;
	@Mock
	private UserRepository userRepository;
	private User user = new User();
	private StockModel stock = new StockModel();
	private Alerts alert = new Alerts();
	private Session session = Session.getInstance(new Properties());
	@Mock
	private Message message;
	
	@BeforeAll
	public void setup(){
		user.setMail("Mail");
		user.setName("Name");
		user.setSurname("surname");
		stock.setStockName("StockName");
		stock.setStockSymbol("TEST");
		alert.setStockSymbol("TEST");
		alert.setAlertPrice(BigDecimal.ONE);
	}
	@Test
	public void prepareMessageNullSessionTest() {
		assertEquals(null,mailService.prepareMessage(null,user.getMail(),user.getName(),stock.getStockName(), alert.getAlertPrice(),stock.getStockSymbol()));
	}
	@Test
	public void prepareMessageNullMailTest() {
		assertEquals(null,mailService.prepareMessage(session,null,user.getName(),stock.getStockName(), alert.getAlertPrice(),stock.getStockSymbol()));
	}
	@Test
	public void prepareMessageNullNameTest() {
		assertEquals(null,mailService.prepareMessage(session,user.getMail(),null,stock.getStockName(), alert.getAlertPrice(),stock.getStockSymbol()));
	}
	@Test
	public void prepareMessageNullStockNameTest() {
		assertEquals(null,mailService.prepareMessage(session,user.getMail(),user.getName(),null, alert.getAlertPrice(),stock.getStockSymbol()));
	}
	@Test
	public void prepareMessageNullPriceTest() {
		assertEquals(null,mailService.prepareMessage(session,user.getMail(),user.getName(),stock.getStockName(), null,stock.getStockSymbol()));
	}
	@Test
	public void prepareMessageNullStockSymbolTest() {
		assertEquals(null,mailService.prepareMessage(session,user.getMail(),user.getName(),stock.getStockName(), alert.getAlertPrice(),null));
	}
	@Test
	public void prepareMessageTrueValueTest() {
		assertEquals(null,mailService.prepareMessage(session,user.getMail(),user.getName(),stock.getStockName(), alert.getAlertPrice(),stock.getStockSymbol()));
	}
}
