package com.summer.core;

import com.summer.adivce.IAdvice;
import com.summer.annotation.PointCut;

import java.util.Map;

/**
 * Created by 10033 on 2017/5/12.
 * 对Bean进行AOP操作
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
                    aopProxy.setClassAdvice(IAdvice);

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
