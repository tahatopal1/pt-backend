package com.project.pt.mapper.user;

import com.project.pt.dto.AddressDTO;
import com.project.pt.dto.user.PersistableUserDTO;
import com.project.pt.mapper.CustomMapper;
import com.project.pt.mapper.CustomMerger;
import com.project.pt.model.Address;
import com.project.pt.model.User;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PersistableUserToUserMapper implements CustomMapper<PersistableUserDTO, User>, CustomMerger<PersistableUserDTO, User> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User map(PersistableUserDTO persistableUserDTO) {
        return User.builder()
                .id(Strings.isNotBlank(persistableUserDTO.getId())
                        ? UUID.fromString(persistableUserDTO.getId())
                        : null)
                .name(persistableUserDTO.getName())
                .surname(persistableUserDTO.getSurname())
                .username(persistableUserDTO.getUsername())
                .password(passwordEncoder.encode(persistableUserDTO.getPassword()))
                .gender(persistableUserDTO.getGender())
                .phoneNumber(persistableUserDTO.getPhoneNumber())
                .birthDate(persistableUserDTO.getBirthDate())
                .address(generateAddress(persistableUserDTO))
                .build();
    }

    @Override
    public void merge(PersistableUserDTO persistableUserDTO, User user) {

        user.setName(persistableUserDTO.getName());
        user.setSurname(persistableUserDTO.getSurname());
        user.setPassword(persistableUserDTO.getPassword());
        user.setPhoneNumber(persistableUserDTO.getPhoneNumber());
        user.setBirthDate(persistableUserDTO.getBirthDate());
        user.setGender(persistableUserDTO.getGender());
        user.setAddress(generateAddress(persistableUserDTO));
    }

    private Address generateAddress(PersistableUserDTO persistableUserDTO) {
        AddressDTO address = persistableUserDTO.getAddress();
        return Address.builder()
                .country(address.getCountry())
                .city(address.getCity())
                .neighbourhood(address.getNeighbourhood())
                .street(address.getStreet())
                .no(address.getNo())
                .build();
    }
}
