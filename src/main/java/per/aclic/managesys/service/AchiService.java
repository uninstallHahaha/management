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
         for(int i=0;i<10;i++){
             Achi ac = new Achi();
             ac.setId(Utils.genUUID());
             ac.setName("学术成果"+i);
             ac.setType(i%3);
             ac.setDetail("成果描述成果描述成果描述成果描述成果描述成果描述" +
                     "成果描述成果描述成果描述成果描述成果描述成果描述" +
                     "成果描述成果描述成果描述成果描述成果描述成果描述");
             ac.setUserid(Utils.genUUID());
             achiMapper.insert(ac);
         }
         return 1;
     }

     public List<Achi> findAll(){
         return achiMapper.selectByExample(new AchiExample());
     }

     public Achi findById(String id){
         return achiMapper.selectByPrimaryKey(id);
     }

    public List<Achi> findAllByUser(String userid) {
        return achiMapper.selectByUserid(userid);
    }
}
