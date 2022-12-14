package com.project.pt.dto.country;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CountryDTO implements Serializable {

    @JsonProperty("name")
    private CountryNameDTO countryName;

}
