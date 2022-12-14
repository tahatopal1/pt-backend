package com.project.pt.bootstrap;

import com.project.pt.service.UserSyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(2)
@Profile("pre-dev")
@Slf4j
public class BootstrapUser implements CommandLineRunner {

    @Autowired
    private UserSyncService userSyncService;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        try{
            userSyncService.syncAllUsers(3);
        }catch (Exception e){
            log.info("En error occured during user synchonizing.");
        }
    }
}
