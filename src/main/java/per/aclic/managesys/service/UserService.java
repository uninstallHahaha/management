package per.aclic.managesys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.aclic.managesys.dao.UserMapper;
import per.aclic.managesys.model.User;
import per.aclic.managesys.model.UserExample;

import java.util.List;

@Service
public class UserService {

    @Autowired(required = false)
    UserMapper userMapper;


    public User getUsers(){
        return userMapper.selectByPrimaryKey("djflajfklj");
    }
}
