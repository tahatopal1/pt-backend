package com.project.pt.dto.user;

import com.project.pt.dto.AddressDTO;
import com.project.pt.dto.BaseEntityDTO;
import com.project.pt.model.User;
import com.project.pt.model.constant.Gender;
import com.project.pt.model.constant.Type;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ReadableUserDTO extends BaseEntityDTO {

    private String name;
    private String surname;
    private Date birthDate;
    private String username;
    private Gender gender;
    private String phoneNumber;
    private Set<String> authorities;
    private AddressDTO address;
    private Set<User> users;

}
