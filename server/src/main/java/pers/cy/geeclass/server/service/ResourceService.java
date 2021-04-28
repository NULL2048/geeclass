package pers.cy.geeclass.server.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pers.cy.geeclass.server.domain.Resource;
import pers.cy.geeclass.server.domain.ResourceExample;
import pers.cy.geeclass.server.dto.ResourceDto;
import pers.cy.geeclass.server.dto.PageDto;
import pers.cy.geeclass.server.mapper.ResourceMapper;
import pers.cy.geeclass.server.util.CopyUtil;
import pers.cy.geeclass.server.util.UuidUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        // 查找第一页，每一页有一条数据
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ResourceExample resourceExample = new ResourceExample();


        List<Resource> resourceList = resourceMapper.selectByExample(resourceExample);
        PageInfo pageInfo = new PageInfo<>(resourceList);
        pageDto.setTotal(pageInfo.getTotal());
        List<ResourceDto> resourceDtoList = new ArrayList<ResourceDto>();

        for (int i = 0, l = resourceList.size(); i < l ; i++) {
            Resource resource = resourceList.get(i);
            ResourceDto resourceDto = new ResourceDto();
            BeanUtils.copyProperties(resource, resourceDto);
            resourceDtoList.add(resourceDto);
        }
        pageDto.setList(resourceDtoList);
    }

    /**
     * 添加课程
     * 保存，id有值时更新，无值时新增
     */
    public void save(ResourceDto resourceDto) {
        Resource resource = CopyUtil.copy(resourceDto, Resource.class);
        if (StringUtils.isEmpty(resourceDto.getId())) {
            this.insert(resource);
        } else {
            this.update(resource);
        }
    }

    /**
     * 新增
     */
    private void insert(Resource resource) {

        resource.setId(UuidUtil.getShortUuid());
        resourceMapper.insert(resource);
    }

    /**
     * 更新
     */
    private void update(Resource resource) {
        resourceMapper.updateByPrimaryKey(resource);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        resourceMapper.deleteByPrimaryKey(id);
    }

}
