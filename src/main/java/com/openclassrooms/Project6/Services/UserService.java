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

    public List<User> getAllAdminUsers() {

        List<User> allUsersList = userRepository.findAll();

        List<User> adminUsersList = allUsersList.stream().filter(u -> u.getRole().getRole().equals("Admin")).collect(Collectors.toList());

        return adminUsersList;
    }

    public void deleteAdminUser(String email) {

        if(userRepository.findByEmail(email).getRole().getRole() == "Admin") {

            userRepository.deleteByEmail(email);
        }
    }

    public void createRegularUser(String email, String password) {

        if(userRepository.findByEmail(email) == null) {

            //Create Role
            String userRole = "Regular";
            Role role = new Role(userRole);

            //Create User and Assign Email, Password and Role
            User user = new User(email, password, role);


            //Create Date and Assign it to the Created User
            Date date = new Date();
            user.setCreatedAt(date);


            //Create AccountType
            String accountTypeString = "Regular";
            AccountType accountType = new AccountType(accountTypeString);


            //Create AccountStatus
            String accountStatusString = "Active";
            AccountStatus accountStatus = new AccountStatus(accountStatusString);


            //Create ConnectionType
            String connectionTypeString = "Regular";
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

    public List<User> getAllRegularUsers() {

        List<User> allUsersList = userRepository.findAll();

        List<User> regularUsersList = allUsersList.stream().filter(u -> u.getRole().getRole().equals("Regular")).collect(Collectors.toList());

        return regularUsersList;
    }

    public User getUserById(int id) {

        return userRepository.findById(id).get();
    }

    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    public void updateUsersEmailAddress(String currentEmailAddress, String password, String newEmailAddress) {

        if(getUserByEmail(currentEmailAddress) != null && getUserByEmail(currentEmailAddress).getPassword().equals(password)) {

            User user = getUserByEmail(currentEmailAddress);
            user.setEmail(newEmailAddress);

            Date date = new Date();
            user.setUpdatedAt(date);

            //Que hacer? save()?
            userRepository.
        }
    }

    public void updateUsersPassword(String emailAddress, String currentPassword, String newPassword) {

        if(getUserByEmail(emailAddress) != null && getUserByEmail(emailAddress).getPassword().equals(currentPassword)) {

            User user = getUserByEmail(emailAddress);
            user.setPassword(newPassword);

            Date date = new Date();
            user.setUpdatedAt(date);

            //Que hacer? save()?
        }
    }

    public void deleteRegularUser(int id) {

        if(getUserById(id).getRoleId().getRoleId() == 2) {

            accountRepository.deleteByUserId(getUserById(id));

            connectionRepository.deleteByUserId(getUserById(id));

            userRepository.deleteById(id);
        }
    }
}
