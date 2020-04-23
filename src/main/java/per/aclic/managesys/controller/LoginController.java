package per.aclic.managesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.model.User;
import per.aclic.managesys.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/signin")
    public Map<String, Object> signin(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpSession session,
            String name, String pass, int mem) {
        HashMap<String, Object> map = new HashMap<>();
        User oneByName = userService.findOneByName(name);
        if (null == oneByName) {
            map.put("stat", 0);
            map.put("data", "账号不存在");
            return map;
        } else {
            if (!oneByName.getPass().equals(pass)) {
                map.put("stat", 0);
                map.put("data", "密码不正确");
                return map;
            } else {
                //cookie
                Cookie nameCookie = new Cookie("name", name);
                Cookie psCookie = new Cookie("pass", pass);
                nameCookie.setPath(request.getContextPath() + "/");
                psCookie.setPath(request.getContextPath() + "/");
                if(mem == 1){
                    nameCookie.setMaxAge(7*24*60*60);
                    psCookie.setMaxAge(7*24*60*60);
                    response.addCookie(nameCookie);
                    response.addCookie(psCookie);
                }else {
                    nameCookie.setMaxAge(0);
                    response.addCookie(nameCookie);
                    psCookie.setMaxAge(0);
                    response.addCookie(psCookie);
                }
                //session
                session.setAttribute("USER", oneByName);
                map.put("stat", 1);
                map.put("data", oneByName.getRole());
                return map;
            }
        }
    }

    @ResponseBody
    @RequestMapping("/signup")
    public Map<String, Object> signup(HttpSession session,
                                      String name, String pass, int role) {
        HashMap<String, Object> map = new HashMap<>();
        User oneByName = userService.findOneByName(name);
        if (oneByName != null) {
            map.put("stat", 0);
            map.put("data", "该账号已存在");
            return map;
        }
        User user = new User(Utils.genUUID(), name, pass, role);
        int addRes = userService.addUser(user);
        if (addRes == 1) {
            session.setAttribute("USER", user);
            map.put("stat", 1);
            map.put("data", "");
            return map;
        } else {
            map.put("stat", 0);
            map.put("data", "注册失败,服务器异常");
            return map;
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "/mm/classic/mmenu/html/login/login";
    }
}
