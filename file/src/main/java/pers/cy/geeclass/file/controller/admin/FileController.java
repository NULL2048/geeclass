package pers.cy.geeclass.file.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pers.cy.geeclass.server.domain.File;
import pers.cy.geeclass.server.dto.FileDto;
import pers.cy.geeclass.server.dto.PageDto;
import pers.cy.geeclass.server.dto.ResponseDto;
import pers.cy.geeclass.server.service.FileService;
import pers.cy.geeclass.server.util.ValidatorUtil;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/file")
public class FileController {

    private static final Logger LOG = LoggerFactory.getLogger(FileController.class);
    public static final String BUSINESS_NAME = "文件";

    @Resource
    private FileService fileService;

    /**
     * 列表查询
     * @param pageDto
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody PageDto pageDto) {
        ResponseDto responseDto = new ResponseDto();
        fileService.list(pageDto);
        responseDto.setContent(pageDto);
        return responseDto;
    }

    /**
     * 保存，id值有时更新，无值时新增
     * @param fileDto
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody FileDto fileDto) {

        // 保存校验
                    ValidatorUtil.require(fileDto.getPath(), "相对路径");
                    ValidatorUtil.length(fileDto.getPath(), "相对路径", 1, 100);
                    ValidatorUtil.length(fileDto.getName(), "文件名", 1, 100);
                    ValidatorUtil.length(fileDto.getSuffix(), "后缀", 1, 10);

        ResponseDto responseDto = new ResponseDto();
        fileService.save(fileDto);
        responseDto.setContent(fileDto);
        return responseDto;
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        fileService.delete(id);
        return responseDto;
    }
}
