package com.lambdaschool.countries.controllers;

import com.lambdaschool.countries.models.Country;
import com.lambdaschool.countries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
public class CountryController
{
    @Autowired
    private CountryRepository countryRepository;

    //endpoints
    // http://localhost:2019/names/all
    @GetMapping(value = "/names/all", produces = "application/json")
    public ResponseEntity<?> findAllCountries() {
        List<Country> countries = new ArrayList<>();

        countryRepository.findAll().iterator().forEachRemaining(countries::add);

        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    // http://localhost:2019/names/start/{letter}
    @GetMapping(value = "/names/start/{letter}", produces = "application/json")
    public ResponseEntity<?> findByName(@PathVariable char letter) {
        List<Country> countryList = new ArrayList<>();
        countryRepository.findAll().iterator().forEachRemaining(countryList::add);

        List<Country> filteredList = filterCountries(countryList, (c) -> c.getName().charAt(0) == Character.toUpperCase(letter));

        return new ResponseEntity<>(filteredList, HttpStatus.OK);
    }

    private List<Country> filterCountries(List<Country> countryList, CheckCountries tester) {
        List<Country> rtnList = new ArrayList<>();

        for(Country c : countryList) {
            if (tester.test((c))){
                rtnList.add(c);
            }
        }
        return rtnList;
    }

    // http://localhost:2019/population/total
    @GetMapping(value = "/population/total", produces = "application/json")
    public ResponseEntity<?> getTotal() {
        List<Country> countryList = new ArrayList<>();
        countryRepository.findAll().iterator().forEachRemaining(countryList::add);

        long total = 0;
        for (Country c : countryList) {
            total += c.getPopulation();
        }
        System.out.println("The total is: " + total);
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    // http://localhost:2019/population/min
    @GetMapping(value = "/population/min", produces = "application/json")
    public ResponseEntity<?> getMin() {
        List<Country> countryList = new ArrayList<>();
        countryRepository.findAll().iterator().forEachRemaining(countryList::add);

        long minPop = countryList.get(0).getPopulation();
        Country minCountry = countryList.get(0);

        for (Country c : countryList) {
            if (c.getPopulation() < minPop) {
             minPop = c.getPopulation();
             minCountry = c;
            }
        }

        System.out.println("The min pop is: " + minPop);
        return new ResponseEntity<>(minCountry, HttpStatus.OK);
    }

    // http://localhost:2019/population/max
    @GetMapping(value = "/population/max", produces = "application/json")
    public ResponseEntity<?> getMax() {
        List<Country> countryList = new ArrayList<>();
        countryRepository.findAll().iterator().forEachRemaining(countryList::add);

        long maxPop = countryList.get(0).getPopulation();
        Country maxCountry = countryList.get(0);

        for (Country c : countryList) {
            if (c.getPopulation() > maxPop) {
                maxPop = c.getPopulation();
                maxCountry = c;
            }
        }

        System.out.println("The min pop is: " + maxPop);
        return new ResponseEntity<>(maxCountry, HttpStatus.OK);
    }
}
