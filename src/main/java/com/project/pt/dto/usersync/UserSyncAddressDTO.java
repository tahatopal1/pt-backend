package com.project.pt.dto.usersync;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserSyncAddressDTO implements Serializable {

    private String street;
    private String buildingNumber;
    private String city;
    private String country;

}
