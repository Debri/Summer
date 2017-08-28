package com.summer.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Liuqi
 *
 * @date: 2017/8/25.
 * @author: liuqi
 * @version: v1.0.0
 */
public class BeanFactory {
    public static Map<String, Object> map=new HashMap();
    private static final String KEY="scan.package";
    //初始化IoC容器
    static {
        AutoInjection.automaticInjection(KEY,map);
        ProxyFactory.makeProxyBean(map);

        //生成代理后重新注入
        for(String key:map.keySet()) {
            Class c=map.get(key).getClass().getSuperclass();
            try {
                AutomaticInjection.reinjection(map,c,map.get(key));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static Object getBean(String name) {
        return map.get(name);
    }
}
