package per.aclic.managesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.aclic.managesys.model.Message;
import per.aclic.managesys.service.MessageService;

@Controller
public class MessageController {

    @Autowired
    MessageService messageService;

    @ResponseBody
    @RequestMapping("/addMessage")
    public int addMessage(Message message){
        return messageService.addMessage();
    }


}
