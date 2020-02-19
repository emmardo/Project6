package com.openclassrooms.Project6.Repositories;

import com.openclassrooms.Project6.Models.Iban;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IbanRepository extends JpaRepository<Iban, Integer> {
}
