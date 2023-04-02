package com.example.homeworkmockito;



import com.example.homeworkmockito.exception.UserNonUniqueException;
import com.example.homeworkmockito.model.User;
import com.example.homeworkmockito.service.UserService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


@Test
@DisplayName("Когда список пользователей заполнен, то возвращается правильный список логинов")
        void getListLogins() {
        when(userRepository.getAllUser()).thenReturn(List.of(new User("test1", "test11"),new User("test2", "test22")));
        assertEquals(List.of("test1", "test2"), userService.getAllCollectUser());

    }

    @Test
    @DisplayName("Когда список пользователей заполнен, то возвращается не пустой список логинов.")
    void getNotEmptyListLogins() {
        when(userRepository.getAllUser()).thenReturn(List.of(new User("Test1", "Test11"), new User("Test2", "Test22")));
        assertNotEquals(List.of(), userService.getAllCollectUser());
    }


    @Test
    @DisplayName("Когда мы добавляем пользователя, который уже существует, мы проверяем исключение.")
    void addAlreadyExistsUser() {
        when(userRepository.getAllUser()).thenReturn(List.of(new User("Test1", "Test11")));
        assertThatThrownBy(() -> userService.addUser("Test1", "Test11")).isInstanceOf(UserNonUniqueException.class);
    }

    @Test
    @DisplayName("Когда пользователь добавляется, мы проверяем его добавлениеn.")
    void checkAddUser() {
        when(userRepository.getAllUser()).thenReturn(List.of());
        when(userRepository.addUser(any(User.class))).thenReturn(null);
        userService.addUser("Test1", "Test11");
        verify(userRepository).addUser(any());
    }

    @Test
    @DisplayName("Когда пользователь с указанным именем пользователя и паролем существует, метод возвращает true.")
    void loginUser() {
        when(userRepository.getAllUser()).thenReturn(List.of(new User("Test1", "Test12")));
        assertTrue(userService.searchUserLogin("Test1", "Test12"));
    }




}
