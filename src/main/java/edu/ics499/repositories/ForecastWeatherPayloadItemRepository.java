package edu.ics499.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ics499.model.payloads.ForecastWeatherPayloadItem;

public interface ForecastWeatherPayloadItemRepository extends JpaRepository<ForecastWeatherPayloadItem, Long> {

}
