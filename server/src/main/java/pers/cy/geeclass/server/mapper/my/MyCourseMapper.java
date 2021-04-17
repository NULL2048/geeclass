package pers.cy.geeclass.server.mapper.my;

import org.apache.ibatis.annotations.Param;
import pers.cy.geeclass.server.dto.CourseDto;
import pers.cy.geeclass.server.dto.SortDto;

import java.util.List;

public interface MyCourseMapper {

//    List<CourseDto> list(@Param("pageDto") CoursePageDto pageDto);

    int updateTime(@Param("courseId") String courseId);

    int updateSort(SortDto sortDto);

    int moveSortsBackward(SortDto sortDto);

    int moveSortsForward(SortDto sortDto);
}