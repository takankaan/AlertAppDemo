package com.deneme.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.deneme.demo.entities.Alerts;
import com.deneme.demo.repos.AlertsRepository;
import com.deneme.demo.requests.SoftDeleteRequest;

@Service
public class AlertsService {
	
	private AlertsRepository alertsRepository;

	public AlertsService(AlertsRepository alertsRepository) {
		this.alertsRepository = alertsRepository;
	}
	public List<Alerts> getAllAlerts(){
		return alertsRepository.findAll();
	}
	public Alerts saveOneAlert( Alerts newAlert) {
		return alertsRepository.save(newAlert);
	}
	public Alerts getOneAlert( Long alertId) {
		return alertsRepository.findById(alertId).orElse(null);
	}
	public void deleteAlert( Long alertId) {
		alertsRepository.deleteById(alertId);
	}
	public Alerts softDeleteAlert(Long alertId,SoftDeleteRequest deleteRequest) {
		Optional<Alerts> alert = alertsRepository.findById(alertId);
		if(alert.isPresent())
		{
			alert.get().setDeleted(deleteRequest.isDeleted());
			alert.get().setUpdatedDate(deleteRequest.getUpdatedDate());
			Alerts updatedAlert = alertsRepository.save(alert.get());
			return updatedAlert;
		}
		else {
			return null;
		}
	}

}
