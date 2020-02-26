package com.openclassrooms.Project6.Repositories;

import com.openclassrooms.Project6.Models.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Integer> {

    Connection findConnectionByUserEmail(String email);
}
