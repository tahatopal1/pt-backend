package com.project.pt.mapper.user;

import com.project.pt.dto.AddressDTO;
import com.project.pt.dto.user.PersistableUserDTO;
import com.project.pt.mapper.CustomMapper;
import com.project.pt.model.Address;
import com.project.pt.model.User;
import org.springframework.stereotype.Component;

@Component
public class PersistableUserToUserMapper implements CustomMapper<PersistableUserDTO, User> {

    @Override
    public User map(PersistableUserDTO persistableUserDTO) {
        return User.builder()
                .name(persistableUserDTO.getName())
                .surname(persistableUserDTO.getSurname())
                .username(persistableUserDTO.getUsername())
                .password(persistableUserDTO.getPassword())
                .birthYear(persistableUserDTO.getBirthYear())
                .gender(persistableUserDTO.getGender())
                .address(generateAddress(persistableUserDTO))
                .type(persistableUserDTO.getType())
                .build();
    }

    private Address generateAddress(PersistableUserDTO persistableUserDTO) {
        AddressDTO address = persistableUserDTO.getAddress();
        return Address.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .neighbourhood(address.getNeighbourhood())
                .street(address.getStreet())
                .no(address.getNo())
                .phoneNumber(address.getPhoneNumber())
                .build();
    }
}
