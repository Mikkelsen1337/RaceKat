package com.example.racekat.application.services;

import com.example.racekat.domain.models.User;
import com.example.racekat.infrastructure.repositories.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Henter alle brugere
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Opretter en ny bruger
    public void createUser(User user) {
        userRepository.save(user);
    }

    // Henter en bruger ud fra ID
    public User getUserByid(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Opdaterer en eksisterende bruger
    public void updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if(existingUser != null) {
            existingUser.setUsername(user.getUsername());
            existingUser.setPassword(user.getPassword());
            existingUser.setEmail(user.getEmail());
            userRepository.save(existingUser);
        }
    }

    // Sletter en bruger
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email ikke fundet" + email));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }
}
