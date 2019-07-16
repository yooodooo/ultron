package com.github.udoo.ultron.common.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author dong.yang
 * @data 2019/7/16 10:45
 */
public final class WebUtil {

    public static boolean isAjax(HttpServletRequest request) {
        return request.getHeader("X-Requested-With") != null;
    }
}
