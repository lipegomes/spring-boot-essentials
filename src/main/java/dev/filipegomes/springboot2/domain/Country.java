package dev.filipegomes.springboot2.domain;

// database world population: https://worldpopulationreview.com/countries

public class Country {
    private String name;

    public Country(String name) {
        this.name = name;
    }

    public Country() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
