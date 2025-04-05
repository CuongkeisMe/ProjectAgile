package com.example.projectagile.service.impl;

import com.example.projectagile.model.User;

public interface IUserService {
    User login(String phoneNumber, String password);
}
