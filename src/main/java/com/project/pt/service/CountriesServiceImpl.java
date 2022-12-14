package com.project.pt.service;

import com.project.pt.constants.ApplicationConstants;
import com.project.pt.dto.country.CountryDTO;
import com.project.pt.dto.country.CountryNameDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountriesServiceImpl implements CountriesService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<String> getAllCountries() {
        return Arrays.asList(restTemplate.getForObject(ApplicationConstants.ALL_COUNTIRES_ENDPOINT, CountryDTO[].class))
                .stream()
                .map(CountryDTO::getCountryName)
                .map(CountryNameDTO::getCommon)
                .sorted()
                .collect(Collectors.toList());
    }
}
