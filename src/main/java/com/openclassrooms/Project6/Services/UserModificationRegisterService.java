package com.openclassrooms.Project6.Services;

import com.openclassrooms.Project6.Models.User;
import com.openclassrooms.Project6.Models.UserModificationRegister;
import com.openclassrooms.Project6.Models.UserModificationType;
import com.openclassrooms.Project6.Repositories.UserModificationRegisterRepository;
import com.openclassrooms.Project6.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserModificationRegisterService {


    @Autowired
    private UserModificationRegisterRepository userModificationRegisterRepository;

    @Autowired
    private UserRepository userRepository;


    public void createUserModificationRegister(String email, String userModificationTypeString, String previousDetails, String newDetails) {

        User user = userRepository.findByEmail(email);

        UserModificationType userModificationType = new UserModificationType(userModificationTypeString);

        Date date = new Date();

        UserModificationRegister userModificationRegister = new UserModificationRegister(user, userModificationType, date);

        if(userModificationTypeString.equals("Email")) {

            userModificationRegister.setPreviousDetails(previousDetails);

            userModificationRegister.setNewDetails(newDetails);
        }

        userModificationRegisterRepository.save(userModificationRegister);
    }


    public UserModificationRegister getSingleUserModificationRegisterById(int userModificationRegisterId) {

        return userModificationRegisterRepository.findUserModificationRegisterById(userModificationRegisterId);
    }


    public List<UserModificationRegister> getAllUsersUserModificationRegistersByEmail(String email) {

        return userModificationRegisterRepository.findUserModificationRegisterByUserEmail(email);
    }


    public List<UserModificationRegister> getAllUserModificationRegisters() {

        return userModificationRegisterRepository.findAll();
    }


    public void deleteUserModificationRegisterById(int userModificationRegisterId) {

        userModificationRegisterRepository.deleteById(userModificationRegisterId);
    }


    public void deleteUsersUserModificationRegistersByEmail(String email) {

        userModificationRegisterRepository.deleteAll(userModificationRegisterRepository.findUserModificationRegisterByUserEmail(email));
    }


    public void deleteAllUserModificationRegisters() {

        userModificationRegisterRepository.deleteAll();
    }
}
