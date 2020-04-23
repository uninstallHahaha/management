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
        return messageMapper.insert(message);
    }


    public List<Message> findByProjid(String id) {
        List<Message> selectByProjid = messageMapper.selectByProjid(id);
        return selectByProjid;
    }

    public int delMessage(String mesid) {
        return messageMapper.deleteByPrimaryKey(mesid);
    }
}
