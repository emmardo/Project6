package com.openclassrooms.Project6.Repositories;

import com.openclassrooms.Project6.Models.UserModificationRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserModificationRegisterRepository extends JpaRepository<UserModificationRegister, Integer> {

    UserModificationRegister findUserModificationRegisterById(int userModificationRegisterId);

    List<UserModificationRegister> findUserModificationRegisterByUserEmail(String email);
}
