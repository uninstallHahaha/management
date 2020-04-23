package per.aclic.managesys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.dao.AchiMapper;
import per.aclic.managesys.model.Achi;
import per.aclic.managesys.model.AchiExample;
import per.aclic.managesys.model.mixmodel.AchiMuser;

import java.util.List;

@Service
public class AchiService {
    
    @Autowired
    AchiMapper achiMapper;
    
     public int addAchi(Achi achi){
         return achiMapper.insert(achi);
     }



     public List<Achi> findAll(){
         return achiMapper.selectByExample(new AchiExample());
     }

     public AchiMuser findById(String id){
         return achiMapper.selectByPrimaryKeyMUser(id);
     }

    public List<Achi> findAllByUser(String userid) {
        return achiMapper.selectByUserid(userid);
    }

    public List<AchiMuser> findAllMUser() {
        return achiMapper.selectAllMUser();
    }

    public Achi findOne(String id) {

         return achiMapper.selectByPrimaryKey(id);
    }

    public int modProj(Achi achi) {
        return achiMapper.updateById(achi);
    }
}
