package pers.cy.geeclass.business.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.cy.geeclass.server.domain.Chapter;
import pers.cy.geeclass.server.service.ChapterService;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @RequestMapping("/chapter")
    public List<Chapter> chapter() {
        return chapterService.list();
    }
}
