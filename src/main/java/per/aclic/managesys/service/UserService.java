package per.aclic.managesys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.aclic.managesys.dao.UserMapper;
import per.aclic.managesys.model.User;
import per.aclic.managesys.model.UserExample;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;


    public User getUsers(){
        return userMapper.selectByPrimaryKey("djflajfklj");
    }

    public User findOneByName(String name){
      return  userMapper.selectByName(name);
    }

    public int addUser(User user){
        return userMapper.insert(user);
    }

    public List<User> findAll(){
        return userMapper.selectByExample(new UserExample());
    }

    public List<User> findAllByCondition(String name) {
        return userMapper.selectByCondition("%"+name+"%");
    }

    public int delUser(String id){
        return userMapper.deleteByPrimaryKey(id);
    }
}
