package com.summer.client.test;

import com.summer.aop.annotation.PointCut;
import com.summer.ioc.annotation.Autowired;
import com.summer.ioc.annotation.MyBean;

/**
 * Created by Liuqi on 2017/9/1
 *
 * @description:
 * @author: debri_liu@163.com
 * @date: 2017/9/1.
 * @since: v1.0.0
 */
@MyBean("classes")
@PointCut("com.summer.BeforeAdviceTest")
public class Classes {

    @Autowired
    private Teacher teacher;
    private String className;

    @PointCut("mySpring.client.BeforeTest")
    public void adviceTest() {
        System.out.println("advice");
    }


    public String getClassName() {
        return className;
    }

    public Classes setClassName(String className) {
        this.className = className;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Classes{");
        sb.append("teacher=").append(teacher);
        sb.append(", className='").append(className).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
