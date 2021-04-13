package pers.cy.geeclass.server.util;

import pers.cy.geeclass.server.exception.ValidatorException;

import org.springframework.util.StringUtils;

/**
 * 前后端分离的项目，后端接口需要增加和前端一样的校验，防止被绕过前端界面，利用第三方工具如postman，直接访问后端接口
 */
public class ValidatorUtil {

    /**
     * 空校验（null or ""）
     */
    public static void require(Object str, String fieldName) {
        if (StringUtils.isEmpty(str)) {
            throw new ValidatorException(fieldName + "不能为空");
        }
    }

    /**
     * 长度校验
     */
    public static void length(String str, String fieldName, int min, int max) {
        if (StringUtils.isEmpty(str)) {
            return;
        }
        int length = 0;
        if (!StringUtils.isEmpty(str)) {
            length = str.length();
        }
        if (length < min || length > max) {
            throw new ValidatorException(fieldName + "长度" + min + "~" + max + "位");
        }
    }
}

