package com.example.Enotes_springboot_project.service;

import com.example.Enotes_springboot_project.entity.User;

public interface UserService {
    public User saveUser(User user);
    public boolean existEmailCheck(String email);
}
