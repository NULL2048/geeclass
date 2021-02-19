package pers.cy.geeclass.server.service;

import org.springframework.stereotype.Service;
import pers.cy.geeclass.server.domain.Chapter;
import pers.cy.geeclass.server.domain.ChapterExample;
import pers.cy.geeclass.server.mapper.ChapterMapper;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChapterService {

    @Resource
    private ChapterMapper chapterMapper;

    public List<Chapter> list() {
        ChapterExample chapterExample = new ChapterExample();

        // 相当于一个where条件  下面这个表示查找id字段为1的数据
        chapterExample.createCriteria().andIdEqualTo("1");

        // 设置排序
        chapterExample.setOrderByClause("id asc");

        return chapterMapper.selectByExample(chapterExample);
    }
}
