package com.lemberski.demo.places;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

import static java.lang.String.format;

@FeignClient("weather")
//@FeignClient(url = "http://localhost:8089", name="weather")
public interface WeatherClient {

    Logger LOG = LoggerFactory.getLogger(WeatherClient.class);

    @SuppressWarnings("rawtypes")
    @GetMapping("/weather/{city}")
    @CircuitBreaker(name = "weather/weather", fallbackMethod = "fallbackWeather")
    Map weather(@PathVariable String city);

    @SuppressWarnings("rawtypes")
    default Map fallbackWeather(String city, Throwable t) {
        LOG.warn("Error getting weather: {}", t.getMessage());
        return Map.of("Default Weather", format("Weather data for '%s' currently not available.", city));
    }

}
