package com.project.pt.service;

import com.project.pt.model.Authority;

public interface AuthorityService {

    void saveAuthority(Authority authority);

    Authority getAuthorityByName(String name);

}
