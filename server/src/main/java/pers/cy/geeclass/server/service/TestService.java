package pers.cy.geeclass.server.service;

import pers.cy.geeclass.server.domain.Test;
import pers.cy.geeclass.server.domain.TestExample;
import pers.cy.geeclass.server.mapper.TestMapper;

import javax.annotation.Resource;
import java.util.List;

public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        TestExample testExample = new TestExample();

        // 相当于一个where条件  下面这个表示查找id字段为1的数据
        testExample.createCriteria().andIdEqualTo(1);

        // 设置排序
        testExample.setOrderByClause("id asc");

        return testMapper.selectByExample(testExample);
    }
}
