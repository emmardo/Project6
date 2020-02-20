package com.openclassrooms.Project6.Services;

import com.openclassrooms.Project6.Models.*;
import com.openclassrooms.Project6.Repositories.AccountRepository;
import com.openclassrooms.Project6.Repositories.ConnectionRepository;
import com.openclassrooms.Project6.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ConnectionRepository connectionRepository;


    //There is NO "UPDATE" functionality for Admin Users
    public void createAdminUser(String email, String password) {

        if(userRepository.findByEmail(email) == null) {

            String adminUser = "Admin";
            Role role = new Role(adminUser);

            User user = new User(email, password, role);

            Date date = new Date();
            user.setCreatedAt(date);

            userRepository.save(user);
        }
    }


    public void createUserByRole(String email, String password, String userRole) {

        if(userRepository.findByEmail(email) == null) {

            //Create Role
            Role role = new Role(userRole);

            //Create User and Assign Email, Password and Role to it
            User user = new User(email, password, role);


            //Create Date and Assign it to the Created User
            Date date = new Date();
            user.setCreatedAt(date);


            //Create AccountType
            String accountTypeString = userRole;
            AccountType accountType = new AccountType(accountTypeString);


            //Create AccountStatus
            String accountStatusString = "Active";
            AccountStatus accountStatus = new AccountStatus(accountStatusString);


            //Create ConnectionType
            String connectionTypeString = userRole;
            ConnectionType connectionType = new ConnectionType(connectionTypeString);


            //Create Connection
            Connection connection = new Connection(connectionType, user);


            //Create Account and Assign it a Balance of 0£
            Account account = new Account(user, accountType, accountStatus, connection, 0);


            userRepository.save(user);

            connectionRepository.save(connection);

            accountRepository.save(account);
        }
    }

    public User getUserByEmail(String userEmail) {

        User newUser = null;

        if(userRepository.findByEmail(userEmail) == null) {

            newUser = userRepository.findByEmail(userEmail);
        }

        return newUser;
    }

    public List<User> getAllUsersByRole(String role) {

        return userRepository.findAll().stream().filter(u -> u.getRole().getRole().equals(role))
                .collect(Collectors.toList());
    }

    public void updateUsersEmailAddress(String currentEmailAddress, String password, String newEmailAddress) {

        if(userRepository.findByEmail(currentEmailAddress) != null
                && userRepository.findByEmail(currentEmailAddress).getPassword().equals(password)) {

            User user = userRepository.findByEmail(currentEmailAddress);
            user.setEmail(newEmailAddress);

            Date date = new Date();
            user.setUpdatedAt(date);

            userRepository.save(user);
        }
    }

    public void updateUsersPassword(String emailAddress, String currentPassword, String newPassword) {

        if(userRepository.findByEmail(emailAddress) != null
                && userRepository.findByEmail(emailAddress).getPassword().equals(currentPassword)) {

            User user = userRepository.findByEmail(emailAddress);
            user.setPassword(newPassword);

            Date date = new Date();
            user.setUpdatedAt(date);

            userRepository.save(user);
        }
    }


    public void deleteUserByEmail(String userEmail, String password, String regularAdminOrCompany) {

        if(userRepository.findByEmail(userEmail) != null && userRepository.findByEmail(userEmail).getRole().getRole().equals(regularAdminOrCompany)
                && userRepository.findByEmail(userEmail).getPassword().equals(password)) {

            accountRepository.delete(accountRepository.findAccountByUserEmail(userEmail));

            connectionRepository.delete(connectionRepository.findConnectionByUserEmail(userEmail));

            userRepository.delete(userRepository.findByEmail(userEmail));
        }
    }
}
