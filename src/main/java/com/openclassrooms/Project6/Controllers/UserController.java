package com.openclassrooms.Project6.Controllers;

import com.openclassrooms.Project6.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/*@RequestMapping("/users")*/
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/login")
    public String showLoginPage() {

        return "login";
    }
}
