package per.aclic.managesys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.aclic.managesys.model.UserTest;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("getUser")
    public UserTest getUser(){
        return new UserTest("alice","1234");
    }
}
