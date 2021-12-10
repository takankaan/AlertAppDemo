package com.deneme.demo.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.deneme.demo.entities.Alerts;
import com.deneme.demo.repos.AlertsRepository;
import com.deneme.demo.requests.SoftDeleteRequest;
import com.deneme.demo.requests.UpdateAlertRequest;
import com.deneme.demo.response.StockIdAndPrice;

@Service
public class AlertsService {
	
	private AlertsRepository alertsRepository;

	public AlertsService(AlertsRepository alertsRepository) {
		this.alertsRepository = alertsRepository;
	}
	public List<Alerts> getAllAlerts(Long userId,Optional<Long> stockId){
		List<Alerts> notDeletedAlerts = new ArrayList<Alerts>();
		if(userId != null && stockId.isPresent())
		{
			for(Alerts alert : alertsRepository.findByUserIdAndStockId(userId,stockId))
				if(!alert.isDeleted())
					notDeletedAlerts.add(alert);
		}
		else if(userId != null)
		{
			for(Alerts alert : alertsRepository.findAllByUserId(userId))
				if(!alert.isDeleted())
					notDeletedAlerts.add(alert);
		}
		return ((notDeletedAlerts.size() == 0) ? null: notDeletedAlerts); 
	}
	//Urldeki id ile alertteki id karşılaştır
	public Alerts saveOneAlert( Alerts newAlert) {
		if(newAlert !=null)
			return alertsRepository.save(newAlert);
		else
			return null;
	}
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
	public void deleteAlert(Long userId, Long alertId) {
		if(alertId != null)
			if(alertsRepository.findByUserIdAndId(userId,alertId) != null)
			{
				alertsRepository.deleteById(alertId);
				System.out.println(alertId + " Alert successfully removed!");
			}
	}
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
	public Alerts updateAlert(Long userId,Long alertId, UpdateAlertRequest updateAlertRequest) {
		if(alertId != null && userId != null && updateAlertRequest != null)
		{
			Alerts alert = alertsRepository.findByUserIdAndId(userId,alertId);
			if(alert != null)
			{
				alert.setAlertPrice(updateAlertRequest.getAlertPrice());
				alert.setAlertDirection(updateAlertRequest.isAlertDirection());
				alert.setUpdatedDate(updateAlertRequest.getUpdatedDate());
				return alertsRepository.save(alert);
			}
			else
				return null;
		}
		else 
			return null;
	}
	
	public void alertChecker(List<StockIdAndPrice> stockPriceList) {
		for(StockIdAndPrice stock : stockPriceList) {
			sendMessage(alertsRepository.findAllByStockIdAndDeletedFalse(stock.getStockId()),stock.getCurrentPrice());
		}
	}
	public void sendMessage(List<Alerts> alertsList,BigDecimal stockCurrentPrice) {
		for(Alerts alert : alertsList)
		{
			if(alert.isAlertDirection()) {
				if(stockCurrentPrice.compareTo(alert.getAlertPrice())>=0)
				{
					System.out.println(alert.getStockId()+" ---- "+alert.getAlertPrice() +"MESAJ GÖNDERİLDİ");
					alert.setDeleted(true);
					alertsRepository.save(alert);
				}
			}
			else
			{
				if(stockCurrentPrice.compareTo(alert.getAlertPrice())<=0)
				{
					System.out.println(alert.getStockId()+" ---- "+alert.getAlertPrice() +"MESAJ GÖNDERİLDİ");
					alert.setDeleted(true);
					alertsRepository.save(alert);
				}
			}
		}
	}

}
