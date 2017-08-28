package com.summer.core;

import org.logicalcobwebs.cglib.proxy.MethodProxy;

/**
 * Created by Liuqi
 *
 * @date: 2017/8/25.
 * @author: liuqi
 * @version: v1.0.0
 */
public class Execute {
    public static Object executeAfter
            (Object o, Object[] objects, MethodProxy methodProxy, AfterAdvice advice) throws Throwable {
        Object object=methodProxy.invokeSuper(o,objects);
        advice.after();
        return object;
    }
    public static Object executeBefore
            (Object o, Object[] objects, MethodProxy methodProxy, BeforeAdvice advice) throws Throwable {
        advice.before();
        Object object=methodProxy.invokeSuper(o,objects);

        return object;
    }
    public static Object executeSurround
            (Object o, Object[] objects, MethodProxy methodProxy, SurroundAdvice advice) throws Throwable {
        advice.before();
        Object object=methodProxy.invokeSuper(o,objects);
        advice.after();
        return object;
    }
}
