package com.rushi.jobportal.controller;

import com.rushi.jobportal.model.UserType;
import com.rushi.jobportal.model.Users;
import com.rushi.jobportal.service.UserService;
import com.rushi.jobportal.service.UsersTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;
    @Autowired
    private UserService userService;

    public UsersController(UsersTypeService usersTypeService) {
        this.usersTypeService = usersTypeService;
    }

    @GetMapping("/register")
    public String register(Model model){
        List<UserType> userTypes = usersTypeService.getAll();
        System.out.println(userTypes);
        model.addAttribute("getAllTypes", userTypes);
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegistration(@Valid Users users, Model model){
        Optional<Users> optionalUsers = userService.checkIfUserExists(users);
        if(optionalUsers.isPresent()){
            model.addAttribute("error", "Email already registered, try to login or register with other mail");
            List<UserType> userTypes = usersTypeService.getAll();
            model.addAttribute("getAllTypes", userTypes);
            model.addAttribute("user", new Users());
            return "register";
        }
        userService.addNew(users);
        return "dashboard";
    }


}
