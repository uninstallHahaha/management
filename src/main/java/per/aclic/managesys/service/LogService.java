package per.aclic.managesys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.aclic.managesys.dao.LogMapper;
import per.aclic.managesys.model.Log;
import per.aclic.managesys.model.LogExample;

import java.util.List;

@Service
public class LogService {

    @Autowired
    LogMapper logMapper;

    public int addLog(Log log){
        return logMapper.insert(log);
    }

    public List<Log> findAll(){
        return logMapper.selectByExample(new LogExample());
    }

    public int delLog(String id){
        return logMapper.deleteByPrimaryKey(id);
    }

    //del list
    public int delLogList(List<String> ids){
        int res = 1;
        for (String id:
                ids) {
            int curres= logMapper.deleteByPrimaryKey(id);
            if(curres != 1){
                res = 0;
            }
        }
        return res;
    }
}
