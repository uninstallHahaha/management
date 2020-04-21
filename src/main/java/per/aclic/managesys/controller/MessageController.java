package per.aclic.managesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.model.Message;
import per.aclic.managesys.service.MessageService;

import javax.rmi.CORBA.Util;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping("/addMessage")
    public String addMessage(String pageType, String projid, String message) {
        Message ms = new Message();
        ms.setUsername("alice");
        ms.setUserid(Utils.genUUID());
        ms.setProjid(projid);
        ms.setId(Utils.genUUID());
        ms.setContent(message);
        int res = messageService.addMessage(ms);
        if(null != pageType && pageType.equals("proj")){
            return "forward:/getProjInfoPage?id="+projid;
        }
        return "forward:/getPage/getAchiDetailPage/"+projid;
    }


}
