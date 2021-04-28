package pers.cy.geeclass.server.mapper.my;

import org.apache.ibatis.annotations.Param;
import pers.cy.geeclass.server.dto.ResourceDto;

import java.util.List;

public interface MyUserMapper {

    List<ResourceDto> findResources(@Param("userId") String userId);

}
