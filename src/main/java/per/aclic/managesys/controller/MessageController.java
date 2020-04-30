package per.aclic.managesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.model.Message;
import per.aclic.managesys.model.User;
import per.aclic.managesys.service.MessageService;

import javax.rmi.CORBA.Util;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/addMessage")
    public String addMessage(
            HttpSession session,
            String pageType, String projid, String message) {
        User user = (User)session.getAttribute("USER");
        if(user == null){
            user = new User(Utils.genUUID(),"test","testpass",1);
        }
        Message ms = new Message();
        ms.setUsername(user.getName());
        ms.setUserid(user.getId());
        ms.setProjid(projid);
        ms.setId(Utils.genUUID());
        ms.setContent(message);
        ms.setCtime(new Date());
        int res = messageService.addMessage(ms);
        if(null != pageType && pageType.equals("proj")){
            return "redirect:/getProjInfoPage?id="+projid;
        }
        return "redirect:/getPage/getAchiDetailPage/"+projid;
    }


    @ResponseBody
    @RequestMapping("/delMessage")
    public int delMessage(String mesid) {
        return messageService.delMessage(mesid);
    }

}
