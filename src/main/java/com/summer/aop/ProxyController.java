package com.summer.aop;

import com.summer.annotation.Ignore;
import com.summer.annotation.PointCut;
import com.summer.aop.adivce.AfterAdvice;
import com.summer.aop.adivce.AfterThrowingAdvice;
import com.summer.aop.adivce.BeforeAdvice;
import com.summer.aop.adivce.IAdvice;
import com.summer.aop.adivce.SurroundAdvice;
import org.logicalcobwebs.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Liuqi on 2017/8/30
 *
 * @description:
 * @author: debri_liu@163.com
 * @date: 2017/8/30.
 * @since: v1.0.0
 */
public class ProxyController {
    public static Object doController(Object o, Object[] objects, Method method, MethodProxy methodProxy) throws Throwable {


        if (method.isAnnotationPresent(Ignore.class) || !method.isAnnotationPresent(PointCut.class)) {
            return methodProxy.invokeSuper(o, objects);
        } else {
            IAdvice advice = getAdvice(method);
            return doAdvice(o, objects, methodProxy, advice);
        }
    }

    public static Object doController(Object o, Object[] objects, Method method, MethodProxy methodProxy, IAdvice advice) throws Throwable {
        if (method.isAnnotationPresent(Ignore.class)) {
            return methodProxy.invokeSuper(o, objects);
        }
        if (method.isAnnotationPresent(PointCut.class)) {
            IAdvice advice1 = getAdvice(method);
            return doAdvice(o, objects, methodProxy, advice1);
        } else {
            return doAdvice(o, objects, methodProxy, advice);
        }
    }

    private static IAdvice getAdvice(Method method) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String classPath = method.getAnnotation(PointCut.class).value();
        IAdvice advice = (IAdvice) Class.forName(classPath).newInstance();
        return advice;
    }

    private static Object doAdvice(Object o, Object[] objects, MethodProxy methodProxy, IAdvice advice) throws Throwable {
        if (advice == null) {
            return null;
        }
        if (advice instanceof BeforeAdvice) {
            return Executor.executeBefore(o, objects, methodProxy, (BeforeAdvice) advice);
        }
        if (advice instanceof AfterAdvice) {
            return Executor.executrAfter(o, objects, methodProxy, (AfterAdvice) advice);
        }
        if (advice instanceof SurroundAdvice) {
            return Executor.executeSurrund(o, objects, methodProxy, (SurroundAdvice) advice);
        }
        if (advice instanceof AfterThrowingAdvice) {
            return Executor.executeAfterThrowing(o, objects, methodProxy, (AfterThrowingAdvice) advice);
        }
        return null;
    }
}
