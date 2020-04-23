package per.aclic.managesys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.dao.ProjMapper;
import per.aclic.managesys.model.Proj;
import per.aclic.managesys.model.ProjExample;
import per.aclic.managesys.model.mixmodel.projMuser;

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

//    根据name模糊查询
    public List<Proj> findAllByCondition(String con){
        return projMapper.selectAllByCondition("%"+con+"%");
    }

    public List<projMuser> findAllProj(){
        return projMapper.selectAllProjWithUser();
    }

    public List<projMuser> findAllAc(){
        return projMapper.selectAllAcWithUser();
    }

    public Proj findOne(String id){
        return projMapper.selectByPrimaryKey(id);
    }


    public projMuser findOneMUser(String projid){
        return projMapper.selectOneMUser(projid);
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

    public int modProj(Proj proj){
       return projMapper.updateByPrimaryKey(proj);
    }


    public int modProjCount(String projid) {
        return projMapper.updateCount(projid);
    }

    public int stopProj(String projid) {
        return projMapper.stopProj(projid);
    }

    public List<projMuser> findAllMUser() {

        return projMapper.selectAllProjMUser();
    }

    public List<projMuser> findAllProjByUser(String userid) {
        return projMapper.selectAllProjByUser(userid);
    }
    public List<projMuser> findAllAcByUser(String userid) {
        return projMapper.selectAllAcByUser(userid);
    }
}
