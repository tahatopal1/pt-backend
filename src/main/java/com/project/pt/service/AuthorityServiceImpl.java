package com.project.pt.service;

import com.project.pt.model.Authority;
import com.project.pt.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void saveAuthority(Authority authority) {
        authorityRepository.save(authority);
    }

    @Override
    public Authority getAuthorityByName(String name) {
        return authorityRepository.findByName(name);
    }
}
