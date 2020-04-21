package per.aclic.managesys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.dao.ProjMapper;
import per.aclic.managesys.model.Proj;
import per.aclic.managesys.model.ProjExample;

import java.util.List;

@Service
public class ProjService {

    @Autowired
    ProjMapper projMapper;

    public List<Proj> findByLimit(int limit){
        return projMapper.selectByLimit(limit);
    }

    public List<Proj> findAll(){
        ProjExample pe = new ProjExample();
        return projMapper.selectByExample(pe);
    }

    public List<Proj> findAllProj(){
        return projMapper.selectAllProjWithUser();
    }


    public int addProj(Proj proj){
//        for(int i = 0;i<10;i++){
//            Proj p = new Proj();
//            p.setCount(99);
//            p.setDetail("项目描述"+i);
//            p.setId(Utils.genUUID());
//            p.setName("项目名称"+i);
//            p.setState(1);
//            p.setType(1);
//            p.setUserid("djflajfklj");
//            projMapper.insert(p);
//        }
        return projMapper.insert(proj);
    }

    //del one
    public int delProj(String id){
        return projMapper.deleteByPrimaryKey(id);
    }

    //del list
    public int delProjList(List<String> ids){
        int res = 1;
        for (String id:
             ids) {
            int curres= projMapper.deleteByPrimaryKey(id);
            if(curres != 1){
                res = 0;
            }
        }
        return res;
    }

}
