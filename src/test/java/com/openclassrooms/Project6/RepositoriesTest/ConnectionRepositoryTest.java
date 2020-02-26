package com.openclassrooms.Project6.RepositoriesTest;

import com.openclassrooms.Project6.Models.Account;
import com.openclassrooms.Project6.Models.Connection;
import com.openclassrooms.Project6.Models.User;
import com.openclassrooms.Project6.Repositories.ConnectionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ConnectionRepositoryTest {

    @Mock
    private ConnectionRepository connectionRepository;

    @Test
    public void findConnectionByUserEmail_ConnectionExists_ConnectionReturned() {

        //Arrange
        String email = "queti@queti.co.uk";

        User user = new User();
        user.setEmail(email);

        Account account = new Account();
        account.setUser(user);
        user.setAccount(account);

        Connection connection = new Connection();
        connection.setUser(user);

        account.setConnection(connection);

        List<Connection> connectionList = new ArrayList<>();
        connectionList.add(connection);

        //Act
        when(connectionRepository.findAll()).thenReturn(connectionList);

        //Assert
        assertEquals(email, connectionRepository.findConnectionByUserEmail(email).getUser().getEmail());
    }
}
