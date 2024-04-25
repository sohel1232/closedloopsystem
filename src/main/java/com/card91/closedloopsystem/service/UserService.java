package com.card91.closedloopsystem.service;

import com.card91.closedloopsystem.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User findUserById(Long userId);

    User findByPhoneNumber(String phoneNumber);

    void save(User newUser);
}
