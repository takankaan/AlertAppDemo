package com.project.alertapp.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.alertapp.entities.Alerts;
import com.project.alertapp.repos.AlertsRepository;
import com.project.alertapp.requests.SoftDeleteRequest;
import com.project.alertapp.requests.UpdateAlertRequest;
import com.project.alertapp.response.StockSymbolAndPrice;

/**
 * AlertsService provides receive, update and delete alerts.
 * @author KaanSarigul
 *
 */
@Service
public class AlertsService {
	@Autowired
	private MailService mailService;
	@Autowired
	private AlertsRepository alertsRepository;
	
	/**
	 * This method checks the userId and StockSymbol and returns all appropriate alerts.
	 * If stockSymbol is not present then it search undeleted alerts by userId.
	 * @param userId Id of requested user.
	 * @param stockSymbol StockSymbol is unique key for stocks. It can be null.
	 * @return Returns all undeleted alerts belongs to a user. If stockSymbol exists, then returns alerts belongs one stock.
	 */
	public List<Alerts> getAllAlerts(Long userId,Optional<String> stockSymbol){
		if(userId != null && stockSymbol.isPresent())
			return alertsRepository.findByUserIdAndStockSymbolAndDeletedFalse(userId,stockSymbol);
		else if(userId != null)
			return alertsRepository.findAllByUserIdAndDeletedFalse(userId);
		return null; 
	}
	/**
	 * This method provides to create a new alert for the user. 
	 * @param newAlert It contains alert details.The details cannot be null.
	 * @return Returns saved alert.
	 */
	public Alerts saveOneAlert( Alerts newAlert) {
		if(newAlert !=null)
			if(newAlert.getAlertPrice() != null && newAlert.getStockSymbol() !=null && newAlert.getUserId() != null)
				return alertsRepository.save(newAlert);
			else
				return null;
		else
			return null;
	}
	/**
	 * This method retrieve an alert of the user. 
	 * @param userId Id of requested user.
	 * @param alertId Id of requested alert.
	 * @return Returns one alert belongs the specified user.
	 */
	public Alerts getOneAlert(Long userId, Long alertId) {
		if(userId !=null)
		{
			if(alertId !=null)
				return alertsRepository.findByUserIdAndId(userId,alertId);
			else 
				return null;
		}
		else
			return null;
	}
	/**
	 * This method delete an alert completely.
	 * @param userId Id of requested user.
	 * @param alertId Id of requested alert.
	 */
	public void deleteAlert(Long userId, Long alertId) {
		if(alertId != null)
			if(alertsRepository.findByUserIdAndId(userId,alertId) != null)
			{
				alertsRepository.deleteById(alertId);
				System.out.println(alertId + " Alert successfully removed!");
			}
	}
	/**
	 * This method changes deleted property of the alert. It means soft delete an alert. 
	 * @param userId Id of requested user.
	 * @param alertId Id of requested alert.
	 * @param deleteRequest It contains deleted property which will change alert's deleted property.
	 * @return Returns updated alert.
	 */
	public Alerts softDeleteAlert(Long userId,Long alertId,SoftDeleteRequest deleteRequest) {
		if(alertId != null && deleteRequest != null)
		{
			Alerts alert = alertsRepository.findByUserIdAndId(userId,alertId);
			if(alert != null)
			{
				alert.setDeleted(deleteRequest.isDeleted());
				alert.setUpdatedDate(deleteRequest.getUpdatedDate());
				Alerts updatedAlert = alertsRepository.save(alert);
				return updatedAlert;
			}
			else 
				return null;
		}
		else 
			return null;
	}
	
	/**
	 * This method changes alert properties using by updateAlertRequest object.
	 * @param userId Id of requested user.
	 * @param alertId Id of requested alert.
	 * @param updateAlertRequest It contains new alert properties.
	 * @return Returns updated alert.
	 */
	public Alerts updateAlert(Long userId,Long alertId, UpdateAlertRequest updateAlertRequest) {
		if(alertId != null && userId != null && updateAlertRequest != null)
		{
			Alerts alert = alertsRepository.findByUserIdAndId(userId,alertId);
			if(alert != null)
			{
				alert.setAlertPrice(updateAlertRequest.getAlertPrice());
				alert.setAlertDirection(updateAlertRequest.isAlertDirection());
				alert.setPermanent(updateAlertRequest.isPermanent());
				alert.setUpdatedDate(updateAlertRequest.getUpdatedDate());
				return alertsRepository.save(alert);
			}
			else
				return null;
		}
		else 
			return null;
	}
	/**
	 * This method sends the current stock price and stockSymbol of all stocks to the alertChecker method to check one by one. 
	 * @param stockPriceList It contains StockSymbolAndPrice object, that object contains StockSymbol and stocks current price.
	 */
	public void stockControl(List<StockSymbolAndPrice> stockPriceList) {
		for(StockSymbolAndPrice stock : stockPriceList) {
			alertChecker(alertsRepository.findAllByStockSymbolAndDeletedFalse(stock.getStockSymbol()),stock.getCurrentPrice());
		}
	}
	/**
	 * This method takes a list of alerts belonging to a stock and compares it with the current price of the stock and triggers the alert as a result of this comparison. 
	 * @param alertsList List of alarms belonging to a stock.
	 * @param stockCurrentPrice Current value of stock.
	 */
	public void alertChecker(List<Alerts> alertsList,BigDecimal stockCurrentPrice) {
		
		for(Alerts alert : alertsList)
		{
			if(alert.isAlertDirection()) {
				if(stockCurrentPrice.compareTo(alert.getAlertPrice())>0)
				{
					mailService.sendMail(alert);
					if(alert.isPermanent())
						alert.setAlertDirection(false);
					else
						alert.setDeleted(true);
					alertsRepository.save(alert);
				}
			}
			else
			{
				if(stockCurrentPrice.compareTo(alert.getAlertPrice())<0)
				{
					mailService.sendMail(alert);
					if(alert.isPermanent())
						alert.setAlertDirection(true);
					else
						alert.setDeleted(true);
					alertsRepository.save(alert);
				}
			}
		}
	}

}
