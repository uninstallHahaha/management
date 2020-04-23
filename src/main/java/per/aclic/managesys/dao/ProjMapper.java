package per.aclic.managesys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import per.aclic.managesys.model.Proj;
import per.aclic.managesys.model.ProjExample;
import per.aclic.managesys.model.mixmodel.projMuser;

@Repository
public interface ProjMapper {
    long countByExample(ProjExample example);

    int deleteByExample(ProjExample example);

    int deleteByPrimaryKey(String id);

    int insert(Proj record);

    int insertSelective(Proj record);

    List<Proj> selectByExample(ProjExample example);

    List<Proj> selectByLimit(int limit);

    List<Proj> selectAllProj();

    List<projMuser> selectAllProjWithUser();

    List<projMuser> selectAllAcWithUser();

    Proj selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Proj record, @Param("example") ProjExample example);

    int updateByExample(@Param("record") Proj record, @Param("example") ProjExample example);

    int updateByPrimaryKeySelective(Proj record);

    int updateByPrimaryKey(Proj record);

    List<Proj> selectAllByCondition(String con);

    int updateCount(String projid);

    projMuser selectOneMUser(String projid);

    int stopProj(String projid);
}