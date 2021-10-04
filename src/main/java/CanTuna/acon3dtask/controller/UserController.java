package CanTuna.acon3dtask.controller;

import CanTuna.acon3dtask.domain.User;
import CanTuna.acon3dtask.service.UserService;
import org.springframework.stereotype.Controller;


import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
    * USER CONTROLLER
    * uri : /users
    * USER 생성
    * uri : POST /user/new
    * USER 로그인
    * uri : POST /users/login
    * (USER 전체 조회)
    * uri : GET /users/all
    * */



}
