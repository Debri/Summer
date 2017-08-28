package com.summer.core;

import com.summer.annotation.Autowired;
import com.summer.annotation.MyBean;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Liuqi
 *
 * @date: 2017/8/28.
 * @author: liuqi
 * @version: v1.0.0
 */
public class AutoInjection {
    public static void automaticInjection(String key, Map mmp) {
        try {
           // List<Class> list = GetClass.getClassList(key);
            List<Class> list = GetClass.getClassList(key);

            for (Class classes : list) {
                //注册
                Map<String, Object> judgeMap = new HashMap();
                //注入
                injection(mmp, classes, judgeMap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //注入并判断是否循环依赖
    private static void injection(Map mmp, Class classes, Map judgeMap)
            throws Exception {
        boolean isExist = classes.isAnnotationPresent(MyBean.class);
        //如果该注解存在
        if (isExist) {
            MyBean myBean = (MyBean) classes.getAnnotation(MyBean.class);
            String beanName = myBean.value(); //获得bean名称
            if (null == judgeMap.get(beanName))
                judgeMap.put(beanName, true);
            else { //又返回依赖他
                throw new Exception("循环依赖");
            }

            if (null == mmp.get(beanName)) { //还没有被注入
                Object beanObj = classes.newInstance(); //获得bean实例

                Field[] fields = classes.getDeclaredFields();
                boolean fieldExist;
                for (Field field : fields) {
                    fieldExist = field.isAnnotationPresent(Autowired.class);

                    if (fieldExist) {
                        String classtype = field.getGenericType().toString();
                        Class fieldClass = Class.forName(classtype.substring(6));

                        //强制设置值 破坏了封装性
                        field.setAccessible(true);

                        if (fieldClass.isAnnotationPresent(MyBean.class)) {//该属性依赖其它Bean
                            MyBean tbean = (MyBean) fieldClass.getAnnotation(MyBean.class);
                            injection(mmp, fieldClass, judgeMap);
                            field.set(beanObj, mmp.get(tbean.value()));

                        } else {  //该属性不依赖其它Bean
                            Object object = fieldClass.newInstance();
                            field.set(beanObj, object);
                        }
                    }
                }
                mmp.put(beanName, beanObj);
            }

        }
    }

    public static void reinjection(Map mmp, Class classes, Object obj) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Field[] fields = classes.getDeclaredFields();
        boolean fieldExist;
        for (Field field : fields) {
            fieldExist = field.isAnnotationPresent(Autowired.class);

            if (fieldExist) {
                String classtype = field.getGenericType().toString();
                Class fieldClass = Class.forName(classtype.substring(6));
                field.setAccessible(true);
                //强制设置值 破坏了封装性
                field.setAccessible(true);

                if (fieldClass.isAnnotationPresent(MyBean.class)) { //该属性依赖其它Bean
                    MyBean tbean = (MyBean) fieldClass.getAnnotation(MyBean.class);
                    field.set(obj, mmp.get(tbean.value()));

                } else { //该属性不依赖其它Bean
                    Object object = fieldClass.newInstance();
                    field.set(obj, object);
                }
            }
        }
    }

}

