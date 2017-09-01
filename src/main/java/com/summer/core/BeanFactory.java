package com.summer.core;


import com.summer.aop.ProxyFactory;
import com.summer.ioc.AutoInjection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Liuqi
 * bean工厂
 *
 * @Date: 2017/8/24.
 * @Version: v1.0.0
 * @author: liuqi
 */

public class BeanFactory {
    public static Map<String, Object> map = new HashMap<String, Object>();
    private static final String KEY = "base.scan.package";

    //初始化IoC容器
    static {
        AutoInjection.automaticInjection(KEY, map);
        ProxyFactory.makeProxyBean(map);

        //生成代理后重新注入
        for (String key : map.keySet()) {
            Class c = map.get(key).getClass().getSuperclass();
            try {
                AutoInjection.reinjection(map, c, map.get(key));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Object getBean(String name) {
        return map.get(name);
    }
}
