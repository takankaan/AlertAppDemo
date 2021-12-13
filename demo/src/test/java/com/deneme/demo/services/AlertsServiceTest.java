package com.deneme.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.deneme.demo.entities.Alerts;
import com.deneme.demo.repos.AlertsRepository;
import com.deneme.demo.requests.SoftDeleteRequest;
import com.deneme.demo.requests.UpdateAlertRequest;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class AlertsServiceTest {
	@InjectMocks
	AlertsService alertsService ;
	
	@Mock
	AlertsRepository alertsRepository;
	
	private List<Alerts> alertsList = new ArrayList<Alerts>();
	Alerts alert = new Alerts();
	SoftDeleteRequest softDeleteRequest = new SoftDeleteRequest();
	UpdateAlertRequest updateAlertRequest = new UpdateAlertRequest();
	
	@BeforeAll
	public void setup() {
		alert.setStockSymbol("DNME");
		alert.setUserId(1L);
		alert.setId(1L);
		alertsList.add(alert);
		
		softDeleteRequest.setDeleted(true);
	}
	
	@Test
	public void getAllAlertsWithoutParamsTest() {
		assertEquals(null, alertsService.getAllAlerts(null, Optional.empty()));
	}
	@Test
	public void getAllAlertsWithUserParamTest() {
		when(alertsRepository.findAllByUserIdAndDeletedFalse(alert.getUserId())).thenReturn(alertsList);
		assertEquals(1, alertsService.getAllAlerts(alert.getId(), Optional.empty()).size());
		verify(alertsRepository, times(1)).findAllByUserIdAndDeletedFalse(alert.getId());
	}
	@Test
	public void getAllAlertsWithUserAndStockParamTest() {
		when(alertsRepository.findByUserIdAndStockSymbolAndDeletedFalse(alert.getUserId(), Optional.of(alert.getStockSymbol()))).thenReturn(alertsList);
		assertEquals(1,alertsService.getAllAlerts(alert.getUserId(), Optional.of(alert.getStockSymbol())).size());
		verify(alertsRepository, times(1)).findByUserIdAndStockSymbolAndDeletedFalse(alert.getUserId(), Optional.of(alert.getStockSymbol()));
	}
	@Test
	public void saveOneAlertNullValue() {
		assertEquals(null, alertsService.saveOneAlert(null));
		verify(alertsRepository, times(0)).save(null);
	}
	@Test
	public void saveOneAlertTrueValue() {
		when(alertsRepository.save(alert)).thenReturn(alert);
		assertEquals(alert, alertsService.saveOneAlert(alert));
		verify(alertsRepository, times(1)).save(alert);
	}
	@Test
	public void getOneAlertNullValue() {
		assertEquals(null, alertsService.getOneAlert(null,null));
		verify(alertsRepository, times(0)).findByUserIdAndId(null,null);
	}
	@Test
	public void getOneAlertEmptyValue() {
		when(alertsRepository.findByUserIdAndId(alert.getUserId(),alert.getId())).thenReturn(null);
		assertEquals(null, alertsService.getOneAlert(alert.getId(),alert.getUserId()));
		verify(alertsRepository, times(1)).findByUserIdAndId(alert.getUserId(),alert.getId());
	}
	@Test
	public void getOneAlertTrueValue() {
		when(alertsRepository.findByUserIdAndId(alert.getUserId(),alert.getId())).thenReturn(alert);
		assertEquals(alert, alertsService.getOneAlert(alert.getId(),alert.getUserId()));
		verify(alertsRepository, times(1)).findByUserIdAndId(alert.getUserId(),alert.getId());
	}
	@Test
	public void deleteAlertNullValue() {
		alertsService.deleteAlert(null,null);
		verify(alertsRepository,times(0)).deleteById(45L);
	}
	@Test
	public void deleteAlertWrongValue() {
		when(alertsRepository.findByUserIdAndId(1L,45L)).thenReturn(null);
		alertsService.deleteAlert(1L,45L);
		verify(alertsRepository,times(0)).deleteById(45L);
	}
	@Test
	public void deleteAlertTrueValue() {
		when(alertsRepository.findByUserIdAndId(alert.getUserId(),alert.getId())).thenReturn(alert);
		alertsService.deleteAlert(alert.getUserId(),alert.getId());
		verify(alertsRepository,times(1)).deleteById(alert.getId());
	}
	@Test
	public void softDeleteAlertNullValueTest() {
		assertEquals(null,alertsService.softDeleteAlert(alert.getUserId(),alert.getId(), null));
		verify(alertsRepository, times(0)).save(alert);
	}
	@Test
	public void softDeleteAlertWrongIdTest() {
		when(alertsRepository.findByUserIdAndId(alert.getUserId(),alert.getId())).thenReturn(null);
		assertEquals(null,alertsService.softDeleteAlert(alert.getUserId(),alert.getId(), softDeleteRequest));
		verify(alertsRepository, times(0)).save(alert);
	}
	@Test
	public void softDeleteAlertTrueValueTest() {
		when(alertsRepository.findByUserIdAndId(alert.getUserId(),alert.getId())).thenReturn(alert);
		when(alertsRepository.save(alert)).thenReturn(alert);
		assertEquals(true,alertsService.softDeleteAlert(alert.getUserId(),alert.getId(), softDeleteRequest).isDeleted());
		verify(alertsRepository, times(1)).save(alert);
	}
	@Test
	public void updateAlertNullValueTest() {
		assertEquals(null,alertsService.updateAlert(alert.getUserId(),alert.getId(), null));
		verify(alertsRepository, times(0)).save(alert);
	}
	@Test
	public void updateAlertWrongIdTest() {
		when(alertsRepository.findByUserIdAndId(alert.getUserId(),alert.getId())).thenReturn(null);
		assertEquals(null,alertsService.updateAlert(alert.getUserId(),alert.getId(), updateAlertRequest));
		verify(alertsRepository, times(0)).save(alert);
	}
	@Test
	public void updateAlertTrueValueTest() {
		when(alertsRepository.findByUserIdAndId(alert.getUserId(),alert.getId())).thenReturn(alert);
		when(alertsRepository.save(alert)).thenReturn(alert);
		assertEquals(alert,alertsService.updateAlert(alert.getUserId(),alert.getId(), updateAlertRequest));
		verify(alertsRepository, times(1)).save(alert);
	}
	
}
