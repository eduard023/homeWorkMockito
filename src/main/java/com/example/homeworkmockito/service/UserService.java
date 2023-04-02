package com.example.homeworkmockito.service;

import com.example.homeworkmockito.UserRepository;
import com.example.homeworkmockito.exception.UserNonUniqueException;
import com.example.homeworkmockito.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(String login, String password) {
        User user = new User(login, password);
        if (login == null || login.isBlank() || password == null || password.isBlank()) {
            throw new IllegalArgumentException("Поле логин/пароль не заполнено!");
        }
        boolean userExist = this.userRepository
                .getAllUser()
                .stream()
                .anyMatch(u ->u.equals(user));
        if (userExist) {
            throw new UserNonUniqueException();
        }
        this.userRepository.addUser(user);
    }

    public List<String> getAllCollectUser() {
        return this.userRepository.getAllUser().stream().map(User::getLogin).collect(Collectors.toList());
    }

    public boolean searchUserLogin(String login, String password) {
        User user = new User(login, password);
        return userRepository.getAllUser().stream().anyMatch(u -> u.equals(user));
    }
}
