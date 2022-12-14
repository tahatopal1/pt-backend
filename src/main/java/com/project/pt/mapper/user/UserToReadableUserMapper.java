package com.project.pt.mapper.user;

import com.project.pt.dto.AddressDTO;
import com.project.pt.dto.user.ReadableUserDTO;
import com.project.pt.mapper.CustomMapper;
import com.project.pt.model.Address;
import com.project.pt.model.User;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class UserToReadableUserMapper implements CustomMapper<User, ReadableUserDTO> {

    @Override
    public ReadableUserDTO map(User user) {
        AddressDTO address = generateAddress(user);
        return ReadableUserDTO.builder()
                .id(user.getId().toString())
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .birthDate(user.getBirthDate())
                .phoneNumber(user.getPhoneNumber())
                .gender(user.getGender())
                .createdDate(user.getCreatedDate())
                .lastModifiedDate(user.getLastModifiedDate())
                .phoneNumber(user.getPhoneNumber())
                .address(address)
                .build();
    }

    private AddressDTO generateAddress(User user) {
        Address address = user.getAddress();
        return AddressDTO.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .neighbourhood(address.getNeighbourhood())
                .street(address.getStreet())
                .no(address.getNo())
                .build();
    }
}
