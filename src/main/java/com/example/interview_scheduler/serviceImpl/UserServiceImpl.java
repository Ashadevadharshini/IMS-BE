package com.example.interview_scheduler.serviceImpl;


import com.example.interview_scheduler.dto.Userdto;
import com.example.interview_scheduler.entity.User;
import com.example.interview_scheduler.repository.UserRepo;
import com.example.interview_scheduler.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User registerUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            user.setRoles(Set.of("ROLE_USER"));
        }

        return userRepo.save(user);
    }



}