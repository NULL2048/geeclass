package pers.cy.geeclass.business.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.cy.geeclass.server.domain.Chapter;
import pers.cy.geeclass.server.dto.ChapterDto;
import pers.cy.geeclass.server.dto.PageDto;
import pers.cy.geeclass.server.service.ChapterService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/chapter")
public class ChapterController {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);
    @Resource
    private ChapterService chapterService;

    @RequestMapping("/list")
    public PageDto list(@RequestBody PageDto pageDto) {
        LOG.info("pageDto:{}", pageDto);
        chapterService.list(pageDto);
        return pageDto;
    }

    @RequestMapping("/save")
    public ChapterDto save(@RequestBody ChapterDto chapterDto) {
        LOG.info("chapterDto:{}", chapterDto);
        chapterService.save(chapterDto);
        return chapterDto;
    }
}
