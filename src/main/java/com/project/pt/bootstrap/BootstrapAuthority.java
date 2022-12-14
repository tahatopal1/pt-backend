package com.project.pt.bootstrap;

import com.project.pt.model.Authority;
import com.project.pt.service.AuthorityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
@Profile("pre-dev-authority")
@Slf4j
public class BootstrapAuthority implements CommandLineRunner {

    @Autowired
    private AuthorityService authorityService;

    @Override
    public void run(String... args) throws Exception {
        List.of("ADMIN", "CUSTOMER", "TRAINER")
                .forEach(s -> {
                    Authority authority = new Authority();
                    authority.setName(s);
                    authority.setDescription(s.concat(" Permission"));
                    authorityService.saveAuthority(authority);
                });
    }
}
