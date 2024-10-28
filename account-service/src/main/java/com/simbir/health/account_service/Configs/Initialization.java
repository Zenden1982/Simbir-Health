package com.simbir.health.account_service.Configs;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.simbir.health.account_service.Class.Role;
import com.simbir.health.account_service.Class.User;
import com.simbir.health.account_service.Repository.RoleRepository;
import com.simbir.health.account_service.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Initialization implements ApplicationRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private void saveRoleIfNotExists(String roleName) {
        Optional<Role> role = roleRepository.findByName(roleName);
        if (role.isEmpty()) {
            roleRepository.save(new Role(null, roleName));
        }
    }

    public void initAdmin() {
        User admin = new User();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setUsername("admin");
        admin.setPassword("MTIzNDU2Nzg=");
        saveRoleIfNotExists("ROLE_ADMIN");
        admin.setRoles(List.of(roleRepository.findByName("ROLE_ADMIN").get()));
        userRepository.save(admin);
    }

    public void initUser() {
        User user = new User();
        user.setFirstName("user");
        user.setLastName("user");
        user.setUsername("user");
        user.setPassword("dXNlcg==");
        saveRoleIfNotExists("ROLE_USER");
        user.setRoles(List.of(roleRepository.findByName("ROLE_USER").get()));
        userRepository.save(user);
    }

    public void initDoctor() {
        User doctor = new User();
        doctor.setFirstName("doctor");
        doctor.setLastName("doctor");
        doctor.setUsername("doctor");
        doctor.setPassword("ZG9jdG9y");
        saveRoleIfNotExists("ROLE_DOCTOR");
        doctor.setRoles(List.of(roleRepository.findByName("ROLE_DOCTOR").get()));
        userRepository.save(doctor);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        saveRoleIfNotExists("ROLE_USER");
        saveRoleIfNotExists("ROLE_DOCTOR");
        saveRoleIfNotExists("ROLE_ADMIN");
        initAdmin();
        initUser();
        initDoctor();
    }

}
