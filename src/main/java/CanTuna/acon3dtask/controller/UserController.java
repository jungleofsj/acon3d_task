package CanTuna.acon3dtask.controller;

import CanTuna.acon3dtask.domain.User;
import CanTuna.acon3dtask.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users/new")
    public String createForm() {
        return "/createUserForm";
    }

    @PostMapping("/users/new")
    public ResponseEntity createUser(@RequestBody User user) {
        if (userService.joinUser(user) == -1L) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping("/users/login")
    public String loginForm() {
        return "/test/login";
    }

    @PostMapping("/users/login")
    public ResponseEntity validUser(@RequestBody User user) {
        Long userType = userService.logInUser(user);

        if (userType == null) {
            return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(userType, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/users/all")
    public ResponseEntity listAll() {
        List<User> users = userService.findUserAll();
        return new ResponseEntity(users, HttpStatus.OK);
    }


}
