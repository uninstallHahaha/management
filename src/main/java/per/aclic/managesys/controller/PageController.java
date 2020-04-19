package per.aclic.managesys.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

//    模板跳转通用公式
    @RequestMapping("/{first}/{second}")
    public String getPage(@PathVariable String first, @PathVariable String second) {
        String preStr = "mm/classic/mmenu/html/";
        String[] splitSec = second.split(".html");
        String resStr = preStr + first + "/" + splitSec[0];
        return resStr;
    }
}
