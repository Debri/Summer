package com.summer.ioc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Liuqi
 *
 * @Date: 2017/8/24.
 * @Version: v1.0.0
 * @author: liuqi
 */
@Target({ElementType.FIELD,  ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Autowired {
}
