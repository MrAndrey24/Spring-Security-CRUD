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
public class UserSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserSeeder(
            RoleRepository roleRepository,
            UserRepository  userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.createUser();
    }

    private void createUser() {
        User user = new User();
        user.setName("Andrey");
        user.setLastname("Acosta");
        user.setEmail("andrey@gmail.com");
        user.setPassword("andrey123");

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var newUser = new User();
        newUser.setName(user.getName());
        newUser.setLastname(user.getLastname());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(optionalRole.get());

        userRepository.save(newUser);
    }
}
