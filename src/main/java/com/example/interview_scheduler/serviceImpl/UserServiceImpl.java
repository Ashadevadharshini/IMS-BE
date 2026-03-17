package com.example.interview_scheduler.serviceImpl;


import com.example.interview_scheduler.dto.Userdto;
import com.example.interview_scheduler.entity.User;
import com.example.interview_scheduler.repository.UserRepo;
import com.example.interview_scheduler.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(Userdto userdto) {
        User user = new User(
                null,
                userdto.getName(),
                userdto.getEmail(),
                passwordEncoder.encode(userdto.getPassword()),
                userdto.getRole()
        );



        return userRepo.save(user);

    }
}
