package com.summer.client;

import com.summer.client.test.Teacher;
import com.summer.core.BeanFactory;

/**
 * Created by Liuqi on 2017/9/1
 *
 * @description:
 * @author: debri_liu@163.com
 * @date: 2017/9/1.
 * @since: v1.0.0
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.summer.core.BeanFactory");
        Teacher teacher = (Teacher) BeanFactory.getBean(Teacher.class.getSimpleName());


    }
}
