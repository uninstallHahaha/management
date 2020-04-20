package per.aclic.managesys.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import per.aclic.managesys.model.Achi;
import per.aclic.managesys.model.AchiExample;

@Repository
public interface AchiMapper {
    long countByExample(AchiExample example);

    int deleteByExample(AchiExample example);

    int deleteByPrimaryKey(String id);

    int insert(Achi record);

    int insertSelective(Achi record);

    List<Achi> selectByExample(AchiExample example);

    Achi selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Achi record, @Param("example") AchiExample example);

    int updateByExample(@Param("record") Achi record, @Param("example") AchiExample example);

    int updateByPrimaryKeySelective(Achi record);

    int updateByPrimaryKey(Achi record);
}