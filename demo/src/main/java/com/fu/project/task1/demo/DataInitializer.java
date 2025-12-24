package com.fu.project.task1.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AppRoleRepository roleRepository;

    @Override
    public void run(String... args) {
        if (roleRepository.count() == 0) {
            AppRole admin = new AppRole();
            admin.setName("ADMIN");
            roleRepository.save(admin);

            AppRole user = new AppRole();
            user.setName("USER");
            roleRepository.save(user);
        }
    }
}