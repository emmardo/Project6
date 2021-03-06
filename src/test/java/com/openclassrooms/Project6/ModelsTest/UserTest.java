package com.openclassrooms.Project6.ModelsTest;

import com.openclassrooms.Project6.Models.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private int id = 1;

    private Role role = new Role();

    private String email = "";

    private String password = "";

    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    private List<UserModificationRegister> userModificationRegisters = new ArrayList<>();

    private Account account = new Account();

    private Connection connection = new Connection();

    private UserModificationRegister userModificationRegister = new UserModificationRegister();

    @Test
    public void parameterizedConstructor() {

        //Act
        User newUser = new User(email, password, role);

        //Assert
        assertEquals(email, newUser.getEmail());
        assertEquals(password, newUser.getPassword());
        assertEquals(role, newUser.getRole());
    }

    @Test
    public void setId() {

        //Arrange
        User newUser = new User();

        //Act
        newUser.setId(id);

        //Assert
        assertEquals(id, newUser.getId());
    }

    @Test
    public void setRole() {

        //Arrange
        User newUser = new User();

        //Act
        newUser.setRole(role);

        //Assert
        assertEquals(role, newUser.getRole());
    }

    @Test
    public void setEmail() {

        //Arrange
        User newUser = new User();

        //Act
        newUser.setEmail(email);

        //Assert
        assertEquals(email, newUser.getEmail());
    }

    @Test
    public void setPassword() {

        //Arrange
        User newUser = new User();

        //Act
        newUser.setPassword(password);

        //Assert
        assertEquals(password, newUser.getPassword());
    }

    @Test
    public void setCreatedAt() {

        //Arrange
        User newUser = new User();

        //Act
        newUser.setCreatedAt(createdAt);

        //Assert
        assertEquals(createdAt, newUser.getCreatedAt());
    }

    @Test
    public void setUpdatedAt() {

        //Arrange
        User newUser = new User();

        //Act
        newUser.setUpdatedAt(updatedAt);

        //Assert
        assertEquals(updatedAt, newUser.getUpdatedAt());
    }

    @Test
    public void setUserModificationRegisters() {

        //Arrange
        User newUser = new User();

        userModificationRegisters.add(userModificationRegister);

        //Act
        newUser.setUserModificationRegisters(userModificationRegisters);

        //Assert
        assertEquals(userModificationRegisters, newUser.getUserModificationRegisters());
    }

    @Test
    public void setAccount() {

        //Arrange
        User newUser = new User();

        //Act
        newUser.setAccount(account);

        //Assert
        assertEquals(account, newUser.getAccount());
    }

    @Test
    public void setConnection() {

        //Arrange
        User newUser = new User();

        //Act
        newUser.setConnection(connection);

        //Assert
        assertEquals(connection, newUser.getConnection());
    }
}
