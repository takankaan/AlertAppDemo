package com.deneme.demo.controllers;

import java.util.List;
import java.util.Optional;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deneme.demo.entities.Alerts;
import com.deneme.demo.requests.SoftDeleteRequest;
import com.deneme.demo.requests.UpdateAlertRequest;
import com.deneme.demo.services.AlertsService;

@RestController
@RequestMapping("{userId}/alerts")
public class AlertsController {
	
	private AlertsService alertsService;
	
	public AlertsController(AlertsService alertsService) {
		this.alertsService = alertsService;
	}
	
	@GetMapping
	public List<Alerts> getAllAlerts(@PathVariable Long userId,@RequestParam Optional<Long> stockId){
		return alertsService.getAllAlerts(userId,stockId);
	}
	
	@GetMapping("/{alertId}")
	public Alerts getOneAlert(@PathVariable Long userId, @PathVariable Long alertId) {
		return alertsService.getOneAlert(userId,alertId);
	}
	
	@PostMapping
	public Alerts saveOneAlert(@RequestBody Alerts newAlert) {
		return alertsService.saveOneAlert(newAlert);
	}
	
	@PutMapping("/{alertId}")
	public Alerts updateAlert(@PathVariable Long userId,@PathVariable Long alertId,@RequestBody UpdateAlertRequest updateAlertRequest) {
		return alertsService.updateAlert(userId,alertId, updateAlertRequest);
	}
	
	@DeleteMapping("/{alertId}/delete")
	public void deleteOneAlert(@PathVariable Long userId,@PathVariable Long alertId) {
		alertsService.deleteAlert(userId, alertId);
	}
	@PutMapping("/{alertId}/delete")
	public Alerts softDeleteAlert(@PathVariable Long userId,@PathVariable Long alertId,@RequestBody SoftDeleteRequest deleteRequest) {
		return alertsService.softDeleteAlert(userId, alertId, deleteRequest);
	}

}
