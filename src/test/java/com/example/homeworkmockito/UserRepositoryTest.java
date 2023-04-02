package com.example.homeworkmockito;

import com.example.homeworkmockito.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    private UserRepository userRepository = new UserRepository();
    private final List<User> USER_LIST = new ArrayList<>();
    private User user;

    @BeforeEach
    public void setUp() {
        USER_LIST.add(new User("login", "password"));
        user = new User("login", "password");
    }

    @Test
    @DisplayName("1 Получение пустого списка пользователей → должен возвращаться пустой список.")
    public void getEmptyUserList() {
        assertEquals(userRepository.getAllUser(), new ArrayList<User>(List.of()), "Список не пуст");

    }

    @Test
    @DisplayName("2 Получение списка пользователей при изначально заполненном сервисе → должны возвращаться те же самые пользователи которых добавляли.")
    public void getNotEmptyUserList(){
        userRepository.addUser(user);
        assertEquals(USER_LIST, userRepository.getAllUser(),"Список пуст");
    }

    @Test
    @DisplayName("3 Поиск пользователя по логину → в случае если такой пользователь есть")
    public void searchUserNotExist(){
        userRepository.addUser(user);
        Optional<User> optionalUser = Optional.of(user);
        assertEquals(userRepository.searchUserByLogin("login"), optionalUser,"Пользователя не существует");
    }
    @Test
    @DisplayName("4 Поиск пользователя по логину → в случае если такого пользователя нет")
    public void searchUserExist(){
        assertNotEquals(userRepository.searchUserByLogin(user.getLogin()),
         Optional.ofNullable(user),
        "Пользователь с таким логином уже существет!");
    }

    @Test
    @DisplayName("5 Поиск пользователя по логину и паролю → в случае если такой пользователь есть")
    public void searchByLoginPasswordUserExist(){
        userRepository.addUser(user);
        Optional<User> optionalUser = Optional.of(user);
        assertEquals(userRepository.searchUserByLoginPassword("login", "password"), optionalUser,"Пользователь не существует");

    }
    @Test
    @DisplayName("6 Поиск пользователя по логину и паролю → в случае когда пароль совпадает с одним из существующих, а логин - нет.")
    public void searchByLoginPasswordLoginNotEquals(){
        userRepository.addUser(user);
        Optional<User> optionalUser = Optional.of(user);
        assertNotEquals(userRepository.searchUserByLoginPassword("login1", "password1"), optionalUser);

    }    @Test
    @DisplayName("7 Поиск пользователя по логину и паролю - в случае когда логин совпадает с одним из существующих, а пароль - нет.")
    public void searchByLoginPasswordLoginEquals(){
        userRepository.addUser(user);
        Optional<User> optionalUser = Optional.of(user);
        assertNotEquals(userRepository.searchUserByLoginPassword("login", "password1"), optionalUser);

    }
}
