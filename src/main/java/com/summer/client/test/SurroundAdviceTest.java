package com.summer.client.test;

import com.summer.aop.adivce.SurroundAdvice;

/**
 * Created by Liuqi on 2017/9/1
 *
 * @description:
 * @author: debri_liu@163.com
 * @date: 2017/9/1.
 * @since: v1.0.0
 */
public class SurroundAdviceTest implements SurroundAdvice {
    public void before() {
        System.out.println("before running advice");

    }

    public void after() {
        System.out.println("after running advice");
    }
}
