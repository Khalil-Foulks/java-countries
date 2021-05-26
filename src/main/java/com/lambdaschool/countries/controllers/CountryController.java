package com.lambdaschool.countries.controllers;

import com.lambdaschool.countries.models.Country;
import com.lambdaschool.countries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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

    // http://localhost:2019/names/start/u
    // http://localhost:2019/population/total
    // http://localhost:2019/population/min
    // http://localhost:2019/population/max
}
