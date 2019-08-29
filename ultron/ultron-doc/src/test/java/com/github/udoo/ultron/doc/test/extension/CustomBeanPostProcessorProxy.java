package com.github.udoo.ultron.doc.test.extension;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author dong.yang
 * @date 2019/8/29 13:54
 */
public class CustomBeanPostProcessorProxy implements InvocationHandler {

    private Object target;

    public CustomBeanPostProcessorProxy(Object object) {
        this.target = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        boolean logAnnotation = shouldProxy(method);
        if (logAnnotation) {
            System.out.println("proxy begin. target: " + target.getClass().getName() + "." + method.getName());
        }
        Object result = method.invoke(target, args);
        if (logAnnotation) {
            System.out.println("proxy end. target: " + target.getClass().getName() + "." + method.getName());
        }
        return result;
    }

    /**
     * 基于interface代理，无法获取到方法上的annotation
     *
     * @param proxyMethod
     * @return
     */
    private boolean shouldProxy(Method proxyMethod) throws Exception {
        Method targetMethod = target.getClass().getMethod(proxyMethod.getName(), proxyMethod.getParameterTypes());
        if (targetMethod != null) {
            return targetMethod.getAnnotation(Log.class) != null;
        }
        return false;
    }
}
