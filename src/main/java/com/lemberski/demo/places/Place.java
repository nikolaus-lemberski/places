package com.lemberski.demo.places;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Place {

    @Id
    private Long id;
    private String name;

}
