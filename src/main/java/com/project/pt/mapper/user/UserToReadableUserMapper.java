package com.project.pt.mapper.user;

import com.project.pt.dto.AddressDTO;
import com.project.pt.dto.user.ReadableUserDTO;
import com.project.pt.mapper.CustomMapper;
import com.project.pt.model.Address;
import com.project.pt.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserToReadableUserMapper implements CustomMapper<User, ReadableUserDTO> {

    @Override
    public ReadableUserDTO map(User user) {
        AddressDTO address = generateAddress(user);
        return ReadableUserDTO.builder()
                .name(user.getName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .birthYear(user.getBirthYear())
                .gender(user.getGender())
                .type(user.getType())
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
                .phoneNumber(address.getPhoneNumber())
                .build();
    }
}
