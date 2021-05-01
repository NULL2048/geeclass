package pers.cy.geeclass.server.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.cy.geeclass.server.domain.Member;
import pers.cy.geeclass.server.domain.MemberExample;
import pers.cy.geeclass.server.dto.MemberDto;
import pers.cy.geeclass.server.dto.PageDto;
import pers.cy.geeclass.server.mapper.MemberMapper;
import pers.cy.geeclass.server.util.CopyUtil;
import pers.cy.geeclass.server.util.UuidUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
        import java.util.Date;

@Service
public class MemberService {

    @Resource
    private MemberMapper memberMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        // 查找第一页，每一页有一条数据
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        MemberExample memberExample = new MemberExample();


        List<Member> memberList = memberMapper.selectByExample(memberExample);
        PageInfo pageInfo = new PageInfo<>(memberList);
        pageDto.setTotal(pageInfo.getTotal());
        List<MemberDto> memberDtoList = new ArrayList<MemberDto>();

        for (int i = 0, l = memberList.size(); i < l ; i++) {
            Member member = memberList.get(i);
            MemberDto memberDto = new MemberDto();
            BeanUtils.copyProperties(member, memberDto);
            memberDtoList.add(memberDto);
        }
        pageDto.setList(memberDtoList);
    }

    /**
     * 添加课程
     * 保存，id有值时更新，无值时新增
     */
    public void save(MemberDto memberDto) {
        Member member = CopyUtil.copy(memberDto, Member.class);
        if (StringUtils.isEmpty(memberDto.getId())) {
            this.insert(member);
        } else {
            this.update(member);
        }
    }

    /**
     * 新增
     */
    private void insert(Member member) {
                Date now = new Date();

        member.setId(UuidUtil.getShortUuid());
        memberMapper.insert(member);
    }

    /**
     * 更新
     */
    private void update(Member member) {
        memberMapper.updateByPrimaryKey(member);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        memberMapper.deleteByPrimaryKey(id);
    }

}
