package per.aclic.managesys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.dao.MessageMapper;
import per.aclic.managesys.model.Message;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    MessageMapper messageMapper;

    public int addMessage(Message message) {
//        for (int i = 0; i < 10; i++) {
//            Message message = new Message();
//            message.setContent("留言留言留言留言留言留言留言留言留言留言留言留言留言留言");
//            message.setId(Utils.genUUID());
//            message.setProjid("0da0fcf4-87c8-4e1f-928f-b112");
//            message.setUserid(Utils.genUUID());
//            message.setUsername("alice");
//            messageMapper.insert(message);
//        }
        return messageMapper.insert(message);
    }


    public List<Message> findByProjid(String id) {
        List<Message> selectByProjid = messageMapper.selectByProjid(id);
        return selectByProjid;
    }
}
