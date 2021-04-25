package pers.cy.geeclass.server.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import pers.cy.geeclass.server.domain.User;
import pers.cy.geeclass.server.domain.UserExample;
import pers.cy.geeclass.server.dto.LoginUserDto;
import pers.cy.geeclass.server.dto.UserDto;
import pers.cy.geeclass.server.dto.PageDto;
import pers.cy.geeclass.server.exception.BusinessException;
import pers.cy.geeclass.server.exception.BusinessExceptionCode;
import pers.cy.geeclass.server.mapper.UserMapper;
import pers.cy.geeclass.server.util.CopyUtil;
import pers.cy.geeclass.server.util.UuidUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);


    @Resource
    private UserMapper userMapper;

    /**
     * 列表查询
     * @param pageDto
     */
    public void list(PageDto pageDto) {
        // 查找第一页，每一页有一条数据
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        UserExample userExample = new UserExample();


        List<User> userList = userMapper.selectByExample(userExample);
        PageInfo pageInfo = new PageInfo<>(userList);
        pageDto.setTotal(pageInfo.getTotal());
        List<UserDto> userDtoList = new ArrayList<UserDto>();

        for (int i = 0, l = userList.size(); i < l ; i++) {
            User user = userList.get(i);
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            userDtoList.add(userDto);
        }
        pageDto.setList(userDtoList);
    }

    /**
     * 添加课程
     * 保存，id有值时更新，无值时新增
     */
    public void save(UserDto userDto) {
        User user = CopyUtil.copy(userDto, User.class);
        if (StringUtils.isEmpty(userDto.getId())) {
            this.insert(user);
        } else {
            this.update(user);
        }
    }

    /**
     * 新增
     */
    private void insert(User user) {
        user.setId(UuidUtil.getShortUuid());
        User userDb = this.selectByLoginName(user.getLoginName());
        if (userDb != null) {
            throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
        }
        userMapper.insert(user);
    }

    /**
     * 更新
     */
    private void update(User user) {
        user.setPassword(null);
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 删除
     */
    public void delete(String id) {
        userMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据登录名查询用户信息
     * @param loginName
     * @return
     */
    public User selectByLoginName(String loginName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andLoginNameEqualTo(loginName);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        } else {
            return userList.get(0);
        }
    }

    /**
     * 重置密码
     * @param userDto
     */
    public void savePassword(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setPassword(userDto.getPassword());
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 登录
     * @param userDto
     */
    public LoginUserDto login(UserDto userDto) {
        User user = selectByLoginName(userDto.getLoginName());
        if (user == null) {
            LOG.info("用户名不存在, {}", userDto.getLoginName());
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        } else {
            if (user.getPassword().equals(userDto.getPassword())) {
                // 登录成功
//                LoginUserDto loginUserDto = CopyUtil.copy(user, LoginUserDto.class);
//                // 为登录用户读取权限
//                setAuth(loginUserDto);
//                return loginUserDto;
                return CopyUtil.copy(user, LoginUserDto.class);
            } else {
                LOG.info("密码不对, 输入密码：{}, 数据库密码：{}", userDto.getPassword(), user.getPassword());
                throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
            }
        }
    }
}
