package pers.cy.geeclass.server.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.cy.geeclass.server.domain.Sms;
import pers.cy.geeclass.server.domain.SmsExample;
import pers.cy.geeclass.server.dto.SmsDto;
import pers.cy.geeclass.server.dto.PageDto;
import pers.cy.geeclass.server.mapper.SmsMapper;
import pers.cy.geeclass.server.util.CopyUtil;
import pers.cy.geeclass.server.util.UuidUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
        import java.util.Date;

@Service
public class SmsService {

    @Resource
    private SmsMapper smsMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        // 查找第一页，每一页有一条数据
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        SmsExample smsExample = new SmsExample();


        List<Sms> smsList = smsMapper.selectByExample(smsExample);
        PageInfo pageInfo = new PageInfo<>(smsList);
        pageDto.setTotal(pageInfo.getTotal());
        List<SmsDto> smsDtoList = new ArrayList<SmsDto>();

        for (int i = 0, l = smsList.size(); i < l ; i++) {
            Sms sms = smsList.get(i);
            SmsDto smsDto = new SmsDto();
            BeanUtils.copyProperties(sms, smsDto);
            smsDtoList.add(smsDto);
        }
        pageDto.setList(smsDtoList);
    }

    /**
     * 添加课程
     * 保存，id有值时更新，无值时新增
     */
    public void save(SmsDto smsDto) {
        Sms sms = CopyUtil.copy(smsDto, Sms.class);
        if (StringUtils.isEmpty(smsDto.getId())) {
            this.insert(sms);
        } else {
            this.update(sms);
        }
    }

    /**
     * 新增
     */
    private void insert(Sms sms) {
                Date now = new Date();

        sms.setId(UuidUtil.getShortUuid());
        smsMapper.insert(sms);
    }

    /**
     * 更新
     */
    private void update(Sms sms) {
        smsMapper.updateByPrimaryKey(sms);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        smsMapper.deleteByPrimaryKey(id);
    }

}
