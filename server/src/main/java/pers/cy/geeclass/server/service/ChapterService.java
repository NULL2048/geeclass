package pers.cy.geeclass.server.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pers.cy.geeclass.server.domain.Chapter;
import pers.cy.geeclass.server.domain.ChapterExample;
import pers.cy.geeclass.server.dto.ChapterDto;
import pers.cy.geeclass.server.mapper.ChapterMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public List<ChapterDto> list() {
        ChapterExample chapterExample = new ChapterExample();

        // 相当于一个where条件  下面这个表示查找id字段为1的数据
        // chapterExample.createCriteria().andIdEqualTo("1");

        // 设置排序
        // chapterExample.setOrderByClause("id asc");

        List<Chapter> chapterList = chapterMapper.selectByExample(chapterExample);
        List<ChapterDto> chapterDtoList = new ArrayList<ChapterDto>();

        for (int i = 0, l = chapterList.size(); i < l ; i++) {
            Chapter chapter = chapterList.get(i);
            ChapterDto chapterDto = new ChapterDto();
            BeanUtils.copyProperties(chapter, chapterDto);
            chapterDtoList.add(chapterDto);
        }
        return chapterDtoList;
    }
}
