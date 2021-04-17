package pers.cy.geeclass.server.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.cy.geeclass.server.domain.Teacher;
import pers.cy.geeclass.server.domain.TeacherExample;
import pers.cy.geeclass.server.dto.TeacherDto;
import pers.cy.geeclass.server.dto.PageDto;
import pers.cy.geeclass.server.mapper.TeacherMapper;
import pers.cy.geeclass.server.util.CopyUtil;
import pers.cy.geeclass.server.util.UuidUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    @Resource
    private TeacherMapper teacherMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        // 查找第一页，每一页有一条数据
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        TeacherExample teacherExample = new TeacherExample();


        List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
        PageInfo pageInfo = new PageInfo<>(teacherList);
        pageDto.setTotal(pageInfo.getTotal());
        List<TeacherDto> teacherDtoList = new ArrayList<TeacherDto>();

        for (int i = 0, l = teacherList.size(); i < l ; i++) {
            Teacher teacher = teacherList.get(i);
            TeacherDto teacherDto = new TeacherDto();
            BeanUtils.copyProperties(teacher, teacherDto);
            teacherDtoList.add(teacherDto);
        }
        pageDto.setList(teacherDtoList);
    }

    /**
     * 添加课程
     * 保存，id有值时更新，无值时新增
     */
    public void save(TeacherDto teacherDto) {
        Teacher teacher = CopyUtil.copy(teacherDto, Teacher.class);
        if (StringUtils.isEmpty(teacherDto.getId())) {
            this.insert(teacher);
        } else {
            this.update(teacher);
        }
    }

    /**
     * 新增
     */
    private void insert(Teacher teacher) {

        teacher.setId(UuidUtil.getShortUuid());
        teacherMapper.insert(teacher);
    }

    /**
     * 更新
     */
    private void update(Teacher teacher) {
        teacherMapper.updateByPrimaryKey(teacher);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        teacherMapper.deleteByPrimaryKey(id);
    }

}
