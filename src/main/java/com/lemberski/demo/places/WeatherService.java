package com.lemberski.demo.places;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WeatherService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weather.service.address}")
    private String weatherServiceAddress;

    @SuppressWarnings("rawtypes")
    @HystrixCommand(fallbackMethod = "getWeatherFallback")
    public Map getWeather(String place) {
        return restTemplate.getForObject(String.format("%s/weather/%s", weatherServiceAddress, place), Map.class);
    }

    @SuppressWarnings("rawtypes")
    public Map getWeatherFallback(String place) {
        return Map.of("locationName", place, "error", "Weather data not available");
    }

}
