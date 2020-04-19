package per.aclic.managesys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import per.aclic.managesys.model.User;
import per.aclic.managesys.model.UserTest;
import per.aclic.managesys.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("getUsers")
    public User getUsers(){
        return userService.getUsers();
    }
}
