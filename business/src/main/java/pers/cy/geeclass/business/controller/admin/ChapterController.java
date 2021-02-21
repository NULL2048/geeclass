package pers.cy.geeclass.business.controller.admin;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.cy.geeclass.server.domain.Chapter;
import pers.cy.geeclass.server.dto.ChapterDto;
import pers.cy.geeclass.server.service.ChapterService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @RequestMapping("/list")
    public List<ChapterDto> list() {
        return chapterService.list();
    }
}
