package com.example.homeworkmockito;

import com.example.homeworkmockito.model.User;

import java.util.*;

public class UserRepository {
    private final  List <User> userList = new ArrayList<>();

    public List<User> getAllUser(){
        return userList;
    }

    public Optional<User> searchUserByLogin(String login){
        return this.userList.stream()
                .filter(user -> user.getLogin().equals(login))
                .findAny();
    }

    public Optional<User> searchUserByLoginPassword(String login, String password){
        return this.userList.stream()
                .filter(user -> user.getLogin().equals(login))
                .filter(user -> user.getPassword().equals(password))
                .findAny();
    }

    public User addUser(User user){
        userList.add(user);
        return user;
    }
}
