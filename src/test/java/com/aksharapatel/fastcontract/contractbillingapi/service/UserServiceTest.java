package com.aksharapatel.fastcontract.contractbillingapi.service;

import com.aksharapatel.fastcontract.contractbillingapi.exception.RecordNotFoundException;
import com.aksharapatel.fastcontract.contractbillingapi.models.User;
import com.aksharapatel.fastcontract.contractbillingapi.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetAllUsers() {
        List<User> userList = userService.getAllUsers();

        Assertions.assertEquals(userList.size(), 3);
    }

    @Test
    public void testGetUserByIdFound() throws Exception {
        Long successUserId = 1L;

        User user = userService.getUserById(successUserId);
        Assertions.assertEquals(user.getUserName(), "Test Contractor 1");
    }

    @Test
    public void testGetUserByIdNotFound() {
        Assertions.assertThrows(RecordNotFoundException.class, () -> {
            Long failUserId = 4L;
            userService.getUserById(failUserId);
        });
    }
}
