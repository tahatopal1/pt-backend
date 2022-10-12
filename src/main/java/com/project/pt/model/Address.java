package com.project.pt.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Embeddable
public class Address{

    private String country;
    private String city;
    private String neighbourhood;
    private String street;
    private String no;
    private String phoneNumber;

}
