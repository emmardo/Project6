package com.openclassrooms.Project6.Services;

import com.openclassrooms.Project6.Models.Role;
import com.openclassrooms.Project6.Repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    public void createRoles(List<String> rolesList) {

        roleRepository.deleteAll();

        for (String role: rolesList) {

            Role roleInstance = new Role(role);

            roleRepository.save(roleInstance);
        }
    }

    public void addRole(String role) {

        Role roleInstance = new Role(role);

        roleRepository.save(roleInstance);
    }

    public List<String> getAllRoles() {

        List<String> roles = null;

        if(!roleRepository.findAll().isEmpty()) {

            for (Role role : roleRepository.findAll()) {

                roles.add(role.getRole());
            }
        }

        return roles;
    }

    public String getRole(int roleId) {

        return roleRepository.findRoleById(roleId).getRole();
    }

    public void deleteRole(String role) {

        roleRepository.delete(roleRepository.findRoleByRole(role));
    }
}
