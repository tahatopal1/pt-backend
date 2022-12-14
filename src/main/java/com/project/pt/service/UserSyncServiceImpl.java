package com.project.pt.service;

import com.project.pt.constants.ApplicationConstants;
import com.project.pt.dto.usersync.UserSyncAddressDTO;
import com.project.pt.dto.usersync.UserSyncDTO;
import com.project.pt.dto.usersync.UserSyncInfoDTO;
import com.project.pt.model.Address;
import com.project.pt.model.Authority;
import com.project.pt.model.User;
import com.project.pt.model.constant.Gender;
import com.project.pt.model.constant.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.stream.IntStream;

@Service
public class UserSyncServiceImpl implements UserSyncService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void syncAllUsers(int repeat) {
        IntStream.range(0, repeat).forEach(value -> {
            UserSyncDTO userSyncDTO = restTemplate.getForObject(ApplicationConstants.ALL_USERS_ENDPOINT, UserSyncDTO.class);
            userSyncDTO.getData().stream().forEach(userSyncInfoDTO -> {
                User user = syncAndCreate(userSyncInfoDTO);
                userService.saveUser(user);
            });
        });
    }

    private User syncAndCreate(UserSyncInfoDTO userSyncInfoDTO) {
        User user = new User();
        user.setName(userSyncInfoDTO.getFirstname());
        user.setSurname(userSyncInfoDTO.getLastname());
        user.setUsername(userSyncInfoDTO.getEmail());
        try {
            user.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(userSyncInfoDTO.getBirthday()));
        } catch (ParseException e) {
            user.setBirthDate(null);
        }
        user.setPassword(passwordEncoder.encode("e2DeSK?At"));
        user.setGender(userSyncInfoDTO.getGender().toLowerCase().equals("male") ? Gender.MALE : Gender.FEMALE);
        user.setPhoneNumber(userSyncInfoDTO.getPhone());

        // Address
        UserSyncAddressDTO userSyncAddressDTO = userSyncInfoDTO.getAddress();
        Address address = new Address();
        address.setCountry(userSyncAddressDTO.getCountry());
        address.setCity(userSyncAddressDTO.getCity());
        address.setNeighbourhood("Downtown");
        address.setStreet(userSyncAddressDTO.getStreet());

        address.setNo(userSyncAddressDTO.getBuildingNumber());
        user.setAddress(address);

        Authority authority = authorityService.getAuthorityByName(new Random().nextBoolean() ? "CUSTOMER" : "TRAINER");
        user.addAuthority(authority);

        return user;
    }

}
