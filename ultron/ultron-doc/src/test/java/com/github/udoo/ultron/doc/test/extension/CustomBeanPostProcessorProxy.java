package com.github.udoo.ultron.doc.test.extension;

import com.github.udoo.ultron.doc.test.extension.common.Log;

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
        long begin = System.currentTimeMillis();
        Object result = method.invoke(target, args);
        if (logAnnotation) {
            System.out.println("target: " + target.getClass().getName() + "." + method.getName() + " cost: " + (System.currentTimeMillis() - begin));
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
