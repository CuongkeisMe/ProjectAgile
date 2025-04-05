package com.example.projectagile.service;

import com.example.projectagile.model.User;

public interface IUserService {
    User login(String phoneNumber, String password);
}
