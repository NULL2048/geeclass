package pers.cy.geeclass.server.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.cy.geeclass.server.domain.RoleResource;
import pers.cy.geeclass.server.domain.RoleResourceExample;

public interface RoleResourceMapper {
    long countByExample(RoleResourceExample example);

    int deleteByExample(RoleResourceExample example);

    int deleteByPrimaryKey(String id);

    int insert(RoleResource record);

    int insertSelective(RoleResource record);

    List<RoleResource> selectByExample(RoleResourceExample example);

    RoleResource selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);

    int updateByExample(@Param("record") RoleResource record, @Param("example") RoleResourceExample example);

    int updateByPrimaryKeySelective(RoleResource record);

    int updateByPrimaryKey(RoleResource record);
}