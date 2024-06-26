package org.example.springsecuritycrud.seeder;


import org.example.springsecuritycrud.entities.Role;
import org.example.springsecuritycrud.entities.User;
import org.example.springsecuritycrud.entities.enums.RoleEnum;
import org.example.springsecuritycrud.repository.RoleRepository;
import org.example.springsecuritycrud.repository.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public AdminSeeder(
            RoleRepository roleRepository,
            UserRepository  userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperAdministrator();
    }

    private void createSuperAdministrator() {
        User superAdminRole = new User();
        superAdminRole.setName("Super");
        superAdminRole.setLastname("Admin");
        superAdminRole.setEmail("super.admin@gmail.com");
        superAdminRole.setPassword("superadmin123");

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN_ROLE);
        Optional<User> optionalUser = userRepository.findByEmail(superAdminRole.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new User();
        user.setName(superAdminRole.getName());
        user.setLastname(superAdminRole.getLastname());
        user.setEmail(superAdminRole.getEmail());
        user.setPassword(passwordEncoder.encode(superAdminRole.getPassword()));
        user.setRole(optionalRole.get());

        userRepository.save(user);
    }
}