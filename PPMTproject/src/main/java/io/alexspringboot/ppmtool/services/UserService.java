package io.alexspringboot.ppmtool.services;

import io.alexspringboot.ppmtool.domain.User;
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
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

        // Username has to be unique

        // Password and confirmPassword must match

        // Do not persist or show the confirmPassword


        return userRepository.save(newUser);
    }

}
