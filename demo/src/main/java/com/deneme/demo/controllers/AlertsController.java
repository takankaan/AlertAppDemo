package com.deneme.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deneme.demo.entities.Alerts;
import com.deneme.demo.requests.SoftDeleteRequest;
import com.deneme.demo.services.AlertsService;

@RestController
@RequestMapping("/alerts")
public class AlertsController {
	
	private AlertsService alertsService;
	
	public AlertsController(AlertsService alertsService) {
		this.alertsService = alertsService;
	}
	
	@GetMapping
	public List<Alerts> getAllAlerts(){
		return alertsService.getAllAlerts();
	}
	
	@GetMapping("/{alertId}")
	public Alerts getOneAlert(@PathVariable Long alertId) {
		return alertsService.getOneAlert(alertId);
	}
	
	@PostMapping
	public Alerts saveOneAlert(@RequestBody Alerts newAlert) {
		return alertsService.saveOneAlert(newAlert);
	}
	
	@DeleteMapping("/{alertId}")
	public void deleteOneAlert(@PathVariable Long alertId) {
		alertsService.deleteAlert(alertId);
	}
	@PutMapping("/{alertId}")
	public Alerts softDeleteUser(@PathVariable Long alertId,@RequestBody SoftDeleteRequest deleteRequest) {
		return alertsService.softDeleteAlert(alertId, deleteRequest);
	}

}
