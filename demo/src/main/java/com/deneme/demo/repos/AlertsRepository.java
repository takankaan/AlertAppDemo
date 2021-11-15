package com.deneme.demo.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deneme.demo.entities.Alerts;

public interface AlertsRepository extends JpaRepository<Alerts, Long> {

}
