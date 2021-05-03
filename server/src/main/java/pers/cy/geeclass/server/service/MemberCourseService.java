package pers.cy.geeclass.server.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import pers.cy.geeclass.server.domain.MemberCourse;
import pers.cy.geeclass.server.domain.MemberCourseExample;
import pers.cy.geeclass.server.dto.MemberCourseDto;
import pers.cy.geeclass.server.dto.PageDto;
import pers.cy.geeclass.server.mapper.MemberCourseMapper;
import pers.cy.geeclass.server.util.CopyUtil;
import pers.cy.geeclass.server.util.UuidUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
        import java.util.Date;

@Service
public class MemberCourseService {

    @Resource
    private MemberCourseMapper memberCourseMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        // 查找第一页，每一页有一条数据
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        MemberCourseExample memberCourseExample = new MemberCourseExample();


        List<MemberCourse> memberCourseList = memberCourseMapper.selectByExample(memberCourseExample);
        PageInfo pageInfo = new PageInfo<>(memberCourseList);
        pageDto.setTotal(pageInfo.getTotal());
        List<MemberCourseDto> memberCourseDtoList = new ArrayList<MemberCourseDto>();

        for (int i = 0, l = memberCourseList.size(); i < l ; i++) {
            MemberCourse memberCourse = memberCourseList.get(i);
            MemberCourseDto memberCourseDto = new MemberCourseDto();
            BeanUtils.copyProperties(memberCourse, memberCourseDto);
            memberCourseDtoList.add(memberCourseDto);
        }
        pageDto.setList(memberCourseDtoList);
    }

    /**
     * 添加课程
     * 保存，id有值时更新，无值时新增
     */
    public void save(MemberCourseDto memberCourseDto) {
        MemberCourse memberCourse = CopyUtil.copy(memberCourseDto, MemberCourse.class);
        if (StringUtils.isEmpty(memberCourseDto.getId())) {
            this.insert(memberCourse);
        } else {
            this.update(memberCourse);
        }
    }

    /**
     * 新增
     */
    private void insert(MemberCourse memberCourse) {
        Date now = new Date();
        memberCourse.setId(UuidUtil.getShortUuid());
        memberCourse.setAt(now);
        memberCourseMapper.insert(memberCourse);
    }

    /**
     * 更新
     */
    private void update(MemberCourse memberCourse) {
        memberCourseMapper.updateByPrimaryKey(memberCourse);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        memberCourseMapper.deleteByPrimaryKey(id);
    }

    /**
     * 报名，先判断是否已报名
     * @param memberCourseDto
     */
    public MemberCourseDto enroll(MemberCourseDto memberCourseDto) {
        MemberCourse memberCourseDb = this.select(memberCourseDto.getMemberId(), memberCourseDto.getCourseId());
        if (memberCourseDb == null) {
            MemberCourse memberCourse = CopyUtil.copy(memberCourseDto, MemberCourse.class);
            this.insert(memberCourse);
            // 将数据库信息全部返回，包括id, at
            return CopyUtil.copy(memberCourse, MemberCourseDto.class);
        } else {
            // 如果已经报名，则直接返回已报名的信息
            return CopyUtil.copy(memberCourseDb, MemberCourseDto.class);
        }
    }

    /**
     * 根据memberId和courseId查询记录
     */
    public MemberCourse select(String memberId, String courseId) {
        MemberCourseExample example = new MemberCourseExample();
        example.createCriteria()
                .andCourseIdEqualTo(courseId)
                .andMemberIdEqualTo(memberId);
        List<MemberCourse> memberCourseList = memberCourseMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(memberCourseList)) {
            return null;
        } else {
            return memberCourseList.get(0);
        }
    }

    /**
     * 获取报名信息
     */
    public MemberCourseDto getEnroll(MemberCourseDto memberCourseDto) {
        MemberCourse memberCourse = this.select(memberCourseDto.getMemberId(), memberCourseDto.getCourseId());
        return CopyUtil.copy(memberCourse, MemberCourseDto.class);
    }
}
