package com.github.udoo.ultron.common;

/**
 * @author dong.yang
 * @data 2019/7/22 15:31
 */
public class UltronException extends RuntimeException {
    private String message;

    public UltronException(String message) {
        super(message);
    }
}
