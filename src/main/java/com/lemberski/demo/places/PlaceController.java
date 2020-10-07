package com.lemberski.demo.places;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
import java.util.Optional;

@RepositoryRestController
public class PlaceController {

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private WeatherClient weatherClient;

    @GetMapping("/places/{id}")
    public ResponseEntity<?> getPlaceWithWeather(@PathVariable Long id) {
        Optional<Place> place = placeRepository.findById(id);
        if (place.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new Object() {
            @JsonUnwrapped
            public Place getPlace() {
                return place.get();
            }

            @SuppressWarnings("rawtypes")
            public Map getWeather() {
                return weatherClient.weather(place.get().getName());
            }
        });
    }

}
