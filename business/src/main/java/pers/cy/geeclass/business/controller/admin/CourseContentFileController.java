package pers.cy.geeclass.business.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pers.cy.geeclass.server.domain.CourseContentFile;
import pers.cy.geeclass.server.dto.CourseContentFileDto;
import pers.cy.geeclass.server.dto.PageDto;
import pers.cy.geeclass.server.dto.ResponseDto;
import pers.cy.geeclass.server.service.CourseContentFileService;
import pers.cy.geeclass.server.util.ValidatorUtil;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/course-content-file")
public class CourseContentFileController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseContentFileController.class);
    public static final String BUSINESS_NAME = "课程内容文件";

    @Resource
    private CourseContentFileService courseContentFileService;

    /**
     * 列表查询
     * @param courseId
     * @return
     */
    @GetMapping("/list/{courseId}")
    public ResponseDto list(@PathVariable String courseId) {
        ResponseDto responseDto = new ResponseDto();
        List<CourseContentFileDto> fileDtoList = courseContentFileService.list(courseId);
//        courseContentFileService.list(pageDto);
        responseDto.setContent(fileDtoList);
        return responseDto;
    }

    /**
     * 保存，id值有时更新，无值时新增
     * @param courseContentFileDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseContentFileDto courseContentFileDto) {

        // 保存校验
                    ValidatorUtil.require(courseContentFileDto.getCourseId(), "课程id");
                    ValidatorUtil.length(courseContentFileDto.getUrl(), "地址", 1, 100);
                    ValidatorUtil.length(courseContentFileDto.getName(), "文件名", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        courseContentFileService.save(courseContentFileDto);
        responseDto.setContent(courseContentFileDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        courseContentFileService.delete(id);
        return responseDto;
    }
}
