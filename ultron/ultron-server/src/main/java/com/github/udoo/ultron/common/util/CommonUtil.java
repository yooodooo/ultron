package com.github.udoo.ultron.common.util;

import com.github.udoo.ultron.common.UltronException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @author dong.yang
 * @data 2019/7/22 15:25
 */
@Slf4j
public final class CommonUtil {

    private CommonUtil() {

    }

    public static <T> T convert(Object target, Class<T> clazz) {
        if (target == null) {
            return null;
        }
        try {
            T t = clazz.newInstance();
            BeanUtils.copyProperties(target, t);
            return t;
        } catch (Exception e) {
            throw new UltronException(e.getMessage());
        }
    }
}
