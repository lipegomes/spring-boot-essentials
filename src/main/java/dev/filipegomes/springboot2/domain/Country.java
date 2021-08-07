package dev.filipegomes.springboot2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Country {
    // database world population: https://worldpopulationreview.com/countries
    private Integer id;
    private String name;
}
