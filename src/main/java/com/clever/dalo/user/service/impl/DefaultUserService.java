package com.clever.dalo.user.service.impl;

import com.clever.dalo.dao.UserRepository;
import com.clever.dalo.dao.model.UserEntity;
import com.clever.dalo.user.exception.UserAlreadyExistsException;
import com.clever.dalo.user.exception.UserNotFoundException;
import com.clever.dalo.user.service.UserService;
import com.clever.dalo.user.web.dto.User;
import com.clever.dalo.user.web.dto.UserCreate;
import com.clever.dalo.user.web.dto.UserUpdate;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public List<User> getUsersAll() {
        return userRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public User getUserById(Long id) {
        return convertToDto(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Override
    public User createUser(UserCreate request) {

        try {
            UserEntity user = new UserEntity(request.getLogin());

            return convertToDto(userRepository.save(user));
        } catch(DataIntegrityViolationException exception) {
            throw new UserAlreadyExistsException(request.getLogin());
        }
    }

    @Override
    public User updateUser(Long id, UserUpdate request) {
        
        UserEntity userToUpdate = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        UserEntity userToSave = new UserEntity(userToUpdate.getLogin());
        try {
            
            return convertToDto(userRepository.save(userToSave));
        } catch(DataIntegrityViolationException exception) {
            throw new UserAlreadyExistsException(request.getLogin());
        }
    }

    @Override
    public void deleteUserById(Long id) {

        try {
            userRepository.deleteById(id);
        } catch(EmptyResultDataAccessException exception) {
            throw new UserNotFoundException(id);
        }
    }

    private User convertToDto(UserEntity userEntity) {

        return new User(userEntity.getLogin());
    }
}
