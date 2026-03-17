package com.example.interview_scheduler.service;


import com.example.interview_scheduler.dto.Userdto;
import com.example.interview_scheduler.entity.User;

public interface UserService {
    User registerUser(Userdto userdto);

}
