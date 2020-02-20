package com.openclassrooms.Project6.Services;

import com.openclassrooms.Project6.Models.Connection;
import com.openclassrooms.Project6.Repositories.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;


    private Connection getConnectionByConnectionId(int connectionId) {

        return connectionRepository.findConnectionById(connectionId);
    }


    private Connection getConnectionByEmail(String email) {

        return connectionRepository.findConnectionByUserEmail(email);
    }


    private Connection getConnectionByUserId(int userId) {

        return connectionRepository.findConnectionByUserId(userId);
    }


    private List<Connection> getAllConnections() {

        return connectionRepository.findAll();
    }


    private List<Connection> getAllRegularConnections() {

        return connectionRepository.findAll().stream().filter(c -> c.getConnectionType().getConnectionType().equals("Regular")).collect(Collectors.toList());
    }


    private List<Connection> getAllCompanyConnections() {

        return connectionRepository.findAll().stream().filter(c -> c.getConnectionType().getConnectionType().equals("Company")).collect(Collectors.toList());
    }
}
