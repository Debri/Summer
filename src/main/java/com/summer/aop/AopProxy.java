package com.summer.aop;

import com.summer.aop.adivce.IAdvice;
import org.logicalcobwebs.cglib.proxy.Enhancer;
import org.logicalcobwebs.cglib.proxy.MethodInterceptor;
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
public class AopProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();
    private IAdvice advice = null;

    public AopProxy() {
    }

    public AopProxy(IAdvice advice) {
        this.advice = advice;
    }

    public Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();

    }
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Object object = null;
        if (advice != null) {
            object = ProxyController.doController(o, objects, method, methodProxy);
        } else {
            object = ProxyController.doController(o, objects, method, methodProxy, advice);
        }
        return object;
    }

    public IAdvice getAdvice() {
        return advice;
    }

    public AopProxy setAdvice(IAdvice advice) {
        this.advice = advice;
        return this;
    }
}
