package per.aclic.managesys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import per.aclic.managesys.Utils.Utils;
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

    @ResponseBody
    @RequestMapping("/addUser")
    public int addUser(){
        for(int i=0;i<20;i++){
            User user = new User(Utils.genUUID(),"vlice",
                    "123",(int)(Math.random()*2+1));
            userService.addUser(user);
        }
        return 1;
    }


    @ResponseBody
    @RequestMapping("/delUser")
    public int delUser(String id){
        return userService.delUser(id);
    }
}
