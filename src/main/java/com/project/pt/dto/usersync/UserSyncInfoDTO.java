package com.project.pt.dto.usersync;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserSyncInfoDTO implements Serializable {

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String birthday;
    private String gender;
    private UserSyncAddressDTO address;


}
