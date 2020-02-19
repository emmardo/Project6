package com.openclassrooms.Project6.Repositories;

import com.openclassrooms.Project6.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleById(int id);

    Role findRoleByRole(String role);
}
