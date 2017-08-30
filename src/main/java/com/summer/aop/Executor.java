package com.summer.aop;

import com.summer.aop.adivce.AfterAdvice;
import com.summer.aop.adivce.AfterThrowingAdvice;
import com.summer.aop.adivce.BeforeAdvice;
import com.summer.aop.adivce.SurroundAdvice;
import org.logicalcobwebs.cglib.proxy.MethodProxy;

/**
 * Created by Liuqi on 2017/8/30
 *
 * @description: 代替执行的类
 * @author: debri_liu@163.com
 * @date: 2017/8/30.
 * @since: v1.0.0
 */
public class Executor {
    public static Object executeBefore(Object o, Object[] objects, MethodProxy methodProxy, BeforeAdvice advice) throws Throwable {
        advice.before();
        Object object = methodProxy.invokeSuper(o, objects);
        return object;
    }

    public static Object executrAfter(Object o, Object[] objects, MethodProxy methodProxy, AfterAdvice advice) throws Throwable {
        Object object = methodProxy.invokeSuper(o, objects);
        advice.after();
        return object;
    }

    public static Object executeSurrund(Object o, Object[] objects, MethodProxy methodProxy, SurroundAdvice advice) throws Throwable {
        advice.before();
        Object object = methodProxy.invokeSuper(o, objects);
        advice.after();
        return object;
    }

    public static Object executeAfterThrowing(Object o, Object[] objects, MethodProxy methodProxy, AfterThrowingAdvice advice) {
        Object object = null;
        try {
            object = methodProxy.invokeSuper(o, objects);
        } catch (Throwable throwable) {
            advice.afterThrowing();
            throwable.printStackTrace();
        }
        return object;
    }
}
