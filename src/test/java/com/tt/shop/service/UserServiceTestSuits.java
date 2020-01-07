package com.tt.shop.service;

import com.tt.shop.domain.User;
import com.tt.shop.domain.enumvalues.Role;
import com.tt.shop.exception.UserNotFoundException;
import com.tt.shop.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class UserServiceTestSuits {

    @Test
    public void testAddUser() {
        //Given
        UserRepository userRepositoryMock = mock(UserRepository.class);
        UserService userService = new UserService(userRepositoryMock);
        User user = new User("Johny", "Bravo", "jbravo", "password", true, Role.USER);
        //When
        userService.addUser(user);
        //Then
        verify(userRepositoryMock, times(1)).save(user);
    }

    @Test
    public void testIsExistById() {
        //Given
        UserRepository userRepositoryMock = mock(UserRepository.class);
        UserService userService = new UserService(userRepositoryMock);
        when(userRepositoryMock.existsById(1L)).thenReturn(true);
        //When
        boolean isExist = userService.isExistById(1L);

        Assert.assertTrue(isExist);
    }

    @Test
    public void testGetUserById() throws UserNotFoundException {
        //Given
        UserRepository userRepositoryMock = mock(UserRepository.class);
        UserService userService = new UserService(userRepositoryMock);
        User user = new User(1L, "Johny", "Bravo", "jbravo", "password", true, Role.USER);

        when(userRepositoryMock.findById(1L)).thenReturn(Optional.of(user));
        //When
        User result = userService.getUserById(1L);
        //Then
        Assert.assertEquals(user.getName(), result.getName());
    }

    @Test
    public void testGetUserByIdThrowingException() throws UserNotFoundException {

        //Given
        UserRepository userRepositoryMock = mock(UserRepository.class);
        UserService userService = new UserService(userRepositoryMock);
        when(userRepositoryMock.findById(2L)).thenReturn(Optional.empty());
        //When
        boolean result = false;

        try {
            User user = userService.getUserById(2L);
        } catch (UserNotFoundException ex) {
            result = true;
        }

        Assert.assertTrue(result);
    }

    @Test
    public void testFindByUsername() {

        //Given
        UserRepository userRepositoryMock = mock(UserRepository.class);
        UserService userService = new UserService(userRepositoryMock);
        User user = new User(1L, "Johny", "Bravo", "jbravo", "password", true, Role.USER);
        when(userRepositoryMock.findByUsername("jbravo")).thenReturn(user);

        //When
        User result = userService.findByUsername("jbravo");
        Assert.assertEquals(user.getName(), result.getName());
        Assert.assertEquals(user.getId(), result.getId());

    }

}
