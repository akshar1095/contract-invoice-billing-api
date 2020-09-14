package com.aksharapatel.fastcontract.contractbillingapi.services;

import com.aksharapatel.fastcontract.contractbillingapi.models.User;
import com.aksharapatel.fastcontract.contractbillingapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAllUsers() {
        return userRepository.findAll();
    }
}
