package pers.cy.geeclass.server.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import pers.cy.geeclass.server.domain.Section;
import pers.cy.geeclass.server.domain.SectionExample;

public interface SectionMapper {
    long countByExample(SectionExample example);

    int deleteByExample(SectionExample example);

    int deleteByPrimaryKey(String id);

    int insert(Section record);

    int insertSelective(Section record);

    List<Section> selectByExample(SectionExample example);

    Section selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Section record, @Param("example") SectionExample example);

    int updateByExample(@Param("record") Section record, @Param("example") SectionExample example);

    int updateByPrimaryKeySelective(Section record);

    int updateByPrimaryKey(Section record);
}