package com.summer.aop.adivce;

/**
 * Created by Liuqi on 2017/8/30
 *
 * @author: debri_liu@163.com
 * @date: 2017/8/30.
 * @since: v1.0.0
 */
public interface SurroundAdvice extends IAdvice {
    void before();
    void after();
}
