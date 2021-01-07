package io.alexspringboot.ppmtool.services;

import io.alexspringboot.ppmtool.domain.User;
import io.alexspringboot.ppmtool.exceptions.UsernameDuplicate;
import io.alexspringboot.ppmtool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser(User newUser) {
        try {
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

            // Username has to be unique
            newUser.setUsername(newUser.getUsername());
            // Password and confirmPassword must match

            // Do not persist or show the confirmPassword
            newUser.setConfirmPassword("");

            return userRepository.save(newUser);
        } catch (Exception e) {
            throw new UsernameDuplicate("Username " + newUser.getUsername() + " already exists!");
        }


    }

}
