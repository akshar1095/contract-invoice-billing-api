package com.aksharapatel.fastcontract.contractbillingapi.services;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.User;
import com.aksharapatel.fastcontract.contractbillingapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {

        List<User> userList = userRepository.findAll();

        if(userList.size() > 0) {
            return userList;
        } else {
            return new ArrayList<>();
        }
    }

    public User getUserById(Long userId) throws RecordNotFoundException {

        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("No user record exists for the given user id");
        }
    }
}
