package com.summer.aop;

import com.summer.aop.adivce.IAdvice;
import com.summer.aop.annotation.PointCut;

import java.util.Map;

/**
 * Created by Liuqi on 2017/8/30
 *
 * @author: debri_liu@163.com
 * @date: 2017/8/30.
 * @since: v1.0.0
 */
public class ProxyFactory {
    public static void makeProxyBean(Map<String, Object> map) {

        for(String key:map.keySet()) {
            AopProxy aopProxy=new AopProxy();
            Object o=map.get(key);
            Class classes=o.getClass();
            //判断是否有类注解
            if(classes.isAnnotationPresent(PointCut.class)) {
                PointCut pointCut= (PointCut) classes.getAnnotation(PointCut.class);
                String classPath=pointCut.value();
                try {
                    IAdvice IAdvice = (IAdvice) Class.forName(classPath).newInstance();
                    aopProxy.setAdvice(IAdvice);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            map.put(key, aopProxy.getProxy(classes));
        }
    }
}
