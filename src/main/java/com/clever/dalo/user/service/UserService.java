package com.clever.dalo.user.service;

import com.clever.dalo.dao.model.UserEntity;
import com.clever.dalo.user.web.dto.User;
import com.clever.dalo.user.web.dto.UserCreate;
import com.clever.dalo.user.web.dto.UserUpdate;

import java.util.List;

public interface UserService {

    List<User> getUsersAll();

    User getUserById(Long id);

    User createUser(UserCreate request);

    User updateUser(Long id, UserUpdate request);

    void deleteUserById(Long id);
}
