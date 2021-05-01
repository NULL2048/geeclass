package pers.cy.geeclass.business.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pers.cy.geeclass.server.domain.Member;
import pers.cy.geeclass.server.dto.MemberDto;
import pers.cy.geeclass.server.dto.PageDto;
import pers.cy.geeclass.server.dto.ResponseDto;
import pers.cy.geeclass.server.service.MemberService;
import pers.cy.geeclass.server.util.ValidatorUtil;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/member")
public class MemberController {

    private static final Logger LOG = LoggerFactory.getLogger(MemberController.class);
    public static final String BUSINESS_NAME = "会员";

    @Resource
    private MemberService memberService;

    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        memberService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id值有时更新，无值时新增
     * @param memberDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody MemberDto memberDto) {

        // 保存校验
                    ValidatorUtil.length(memberDto.getMobile(), "手机号", 1, 11);
                    ValidatorUtil.require(memberDto.getPassword(), "密码");
                    ValidatorUtil.length(memberDto.getName(), "昵称", 1, 50);
                    ValidatorUtil.length(memberDto.getPhoto(), "头像url", 1, 200);

        ResponseDto responseDto = new ResponseDto();
        memberService.save(memberDto);
        responseDto.setContent(memberDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        memberService.delete(id);
        return responseDto;
    }
}
