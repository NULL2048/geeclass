package pers.cy.geeclass.business.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pers.cy.geeclass.server.domain.Sms;
import pers.cy.geeclass.server.dto.SmsDto;
import pers.cy.geeclass.server.dto.PageDto;
import pers.cy.geeclass.server.dto.ResponseDto;
import pers.cy.geeclass.server.service.SmsService;
import pers.cy.geeclass.server.util.ValidatorUtil;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/sms")
public class SmsController {

    private static final Logger LOG = LoggerFactory.getLogger(SmsController.class);
    public static final String BUSINESS_NAME = "短信验证码";

    @Resource
    private SmsService smsService;

    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        smsService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id值有时更新，无值时新增
     * @param smsDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SmsDto smsDto) {

        // 保存校验
                    ValidatorUtil.require(smsDto.getMobile(), "手机号");
                    ValidatorUtil.length(smsDto.getMobile(), "手机号", 1, 50);
                    ValidatorUtil.require(smsDto.getCode(), "验证码");
                    ValidatorUtil.require(smsDto.getUse(), "用途");
                    ValidatorUtil.require(smsDto.getAt(), "生成时间");
                    ValidatorUtil.require(smsDto.getStatus(), "用途");

        ResponseDto responseDto = new ResponseDto();
        smsService.save(smsDto);
        responseDto.setContent(smsDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        smsService.delete(id);
        return responseDto;
    }
}
