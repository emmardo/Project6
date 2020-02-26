package com.openclassrooms.Project6.Services;

import com.openclassrooms.Project6.Models.Connection;
import com.openclassrooms.Project6.Repositories.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;


    private Connection getConnectionByEmail(String email) {

        return connectionRepository.findConnectionByUserEmail(email);
    }


    /*private List<Connection> getAllConnections() {

        return connectionRepository.findAll();
    }


    private List<Connection> getAllConnectionsByType(String connectionType) {

        return connectionRepository.findAll().stream()
                .filter(c -> c.getConnectionType().getConnectionType().equals(connectionType))
                .collect(Collectors.toList());
    }*/
}
