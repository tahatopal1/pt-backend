package com.project.pt.controller;

import com.project.pt.dto.ErrorDTO;
import com.project.pt.service.CountriesService;
import com.project.pt.utils.UtilsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountriesController {

    @Autowired
    private CountriesService countriesService;

    @Autowired
    private UtilsService utilsService;

    @GetMapping("/countries")
    public ResponseEntity getAllCountries(){
        try{
            List<String> countries = countriesService.getAllCountries();
            return new ResponseEntity<List<String>>(countries, HttpStatus.OK);
        }catch (Exception e){
            ErrorDTO error = utilsService.generateError(e);
            return new ResponseEntity<ErrorDTO>(error, HttpStatus.BAD_REQUEST);
        }
    }

}
