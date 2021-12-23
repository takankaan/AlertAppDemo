package com.project.alertapp.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.alertapp.entities.Alerts;
import com.project.alertapp.requests.SoftDeleteRequest;
import com.project.alertapp.requests.UpdateAlertRequest;
import com.project.alertapp.services.AlertsService;

/**
 * This class provides REST request for alerts.
 * UserId cannot be null.
 * @author KaanSarigul
 *
 */
@RestController
@RequestMapping("{userId}/alerts")
public class AlertsController {
	@Autowired
	private AlertsService alertsService;
	
	/**
	 * This method sends a request to alertsService to get all alerts.
	 * @param userId The id of the user who the alerts belong to.
	 * @param stockSymbol Not mandatory.If this parameter exists, only alerts of one stock are returned.
	 * @return List of alerts that match the criteria.
	 */
	@GetMapping
	public List<Alerts> getAllAlerts(@PathVariable Long userId,@RequestParam Optional<String> stockSymbol){
		return alertsService.getAllAlerts(userId,stockSymbol);
	}
	/**
	 *  This method sends a request to alertsService to get one alert.
	 * @param userId The id of the user who the alert belong to.
	 * @param alertId The specified alarm must belong to the user. 
	 * @return Returns one alert.
	 */
	@GetMapping("/{alertId}")
	public Alerts getOneAlert(@PathVariable Long userId, @PathVariable Long alertId) {
		return alertsService.getOneAlert(userId,alertId);
	}
	/**
	 * This method sends a request to alertsService to save a new alert. 
	 * @param newAlert Alert type object containing alert values. 
	 * @return Returns saved alert.
	 */
	@PostMapping
	public Alerts saveOneAlert(@RequestBody Alerts newAlert) {
		return alertsService.saveOneAlert(newAlert);
	}
	/**
	 * This method sends a request to alertsService to update an alert. 
	 * @param userId The id of the user who the alert belong to.
	 * @param alertId The specified alarm must belong to the user. 
	 * @param updateAlertRequest Contains alert specifications, cannot be null.
	 * @return Returns updated alert.
	 */
	@PutMapping("/{alertId}")
	public Alerts updateAlert(@PathVariable Long userId,@PathVariable Long alertId,@RequestBody UpdateAlertRequest updateAlertRequest) {
		return alertsService.updateAlert(userId,alertId, updateAlertRequest);
	}
	
	/**
	 * This method sends a request to alertsService to delete an alert. 
	 * @param userId The id of the user who the alert belong to.
	 * @param alertId The specified alarm must belong to the user. 
	 */
	@DeleteMapping("/{alertId}/delete")
	public void deleteOneAlert(@PathVariable Long userId,@PathVariable Long alertId) {
		alertsService.deleteAlert(userId, alertId);
	}
	/**
	 * This method sends a request to alertsService to update soft delete situation an alert. 
	 * @param userId The id of the user who the alert belong to.
	 * @param alertId The specified alarm must belong to the user. 
	 * @param deleteRequest SoftDeleteRequest object contains soft delete situation for an object.
	 * @return Returns updated alert.
	 */
	@PutMapping("/{alertId}/delete")
	public Alerts softDeleteAlert(@PathVariable Long userId,@PathVariable Long alertId,@RequestBody SoftDeleteRequest deleteRequest) {
		return alertsService.softDeleteAlert(userId, alertId, deleteRequest);
	}

}
