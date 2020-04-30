package per.aclic.managesys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.model.User;
import per.aclic.managesys.model.UserTest;
import per.aclic.managesys.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


    @ResponseBody
    @RequestMapping("/modPass")
    public Map<String,Object> modPass(
            HttpSession session,
            String oldpass,
            String pass){
        Map<String,Object> map = new HashMap<String,Object>();
        User user = (User)session.getAttribute("USER");
        if(!user.getPass().equals(oldpass)){
            map.put("stat",0);
            map.put("data","原密码错误");
            return map;
        }
        String userid = user.getId();
        int res = userService.modPass(userid, pass);
        if(res == 1){
            map.put("stat",1);
            map.put("data","修改成功");
            return map;
        }else{
            map.put("stat",0);
            map.put("data","修改失败");
            return map;
        }
    }
}
