package pers.cy.geeclass.server.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import pers.cy.geeclass.server.domain.CourseCategory;
import pers.cy.geeclass.server.domain.CourseCategoryExample;
import pers.cy.geeclass.server.dto.CategoryDto;
import pers.cy.geeclass.server.dto.CourseCategoryDto;
import pers.cy.geeclass.server.dto.PageDto;
import pers.cy.geeclass.server.mapper.CourseCategoryMapper;
import pers.cy.geeclass.server.util.CopyUtil;
import pers.cy.geeclass.server.util.UuidUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseCategoryService {

    @Resource
    private CourseCategoryMapper courseCategoryMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        // 查找第一页，每一页有一条数据
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        CourseCategoryExample courseCategoryExample = new CourseCategoryExample();


        List<CourseCategory> courseCategoryList = courseCategoryMapper.selectByExample(courseCategoryExample);
        PageInfo pageInfo = new PageInfo<>(courseCategoryList);
        pageDto.setTotal(pageInfo.getTotal());
        List<CourseCategoryDto> courseCategoryDtoList = new ArrayList<CourseCategoryDto>();

        for (int i = 0, l = courseCategoryList.size(); i < l ; i++) {
            CourseCategory courseCategory = courseCategoryList.get(i);
            CourseCategoryDto courseCategoryDto = new CourseCategoryDto();
            BeanUtils.copyProperties(courseCategory, courseCategoryDto);
            courseCategoryDtoList.add(courseCategoryDto);
        }
        pageDto.setList(courseCategoryDtoList);
    }

    /**
     * 添加课程
     * 保存，id有值时更新，无值时新增
     */
    public void save(CourseCategoryDto courseCategoryDto) {
        CourseCategory courseCategory = CopyUtil.copy(courseCategoryDto, CourseCategory.class);
        if (StringUtils.isEmpty(courseCategoryDto.getId())) {
            this.insert(courseCategory);
        } else {
            this.update(courseCategory);
        }
    }

    /**
     * 新增
     */
    private void insert(CourseCategory courseCategory) {

        courseCategory.setId(UuidUtil.getShortUuid());
        courseCategoryMapper.insert(courseCategory);
    }

    /**
     * 更新
     */
    private void update(CourseCategory courseCategory) {
        courseCategoryMapper.updateByPrimaryKey(courseCategory);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        courseCategoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据某一课程，先清空课程分类，再保存课程分类
     * @param dtoList
     */
    @Transactional
    public void saveBatch(String courseId, List<CategoryDto> dtoList) {
        CourseCategoryExample example = new CourseCategoryExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        courseCategoryMapper.deleteByExample(example);
        for (int i = 0, l = dtoList.size(); i < l; i++) {
            CategoryDto categoryDto = dtoList.get(i);
            CourseCategory courseCategory = new CourseCategory();
            courseCategory.setId(UuidUtil.getShortUuid());
            courseCategory.setCourseId(courseId);
            courseCategory.setCategoryId(categoryDto.getId());
            insert(courseCategory);
        }
    }

}
