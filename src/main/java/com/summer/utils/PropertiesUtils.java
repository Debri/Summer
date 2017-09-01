package com.summer.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Liuqi on 2017/9/1
 *
 * @description:
 * @author: debri_liu@163.com
 * @date: 2017/9/1.
 * @since: v1.0.0
 */
public class PropertiesUtils {

    public static Map<String, String> readProperties(String property) {
        Properties properties = new Properties();
        Map<String, String> result = new HashMap<String, String>();
        InputStream inputStream = PropertiesUtils.class.getResourceAsStream(property);
        try {
            properties.load(inputStream);
            Enumeration enumeration = properties.propertyNames();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                String value = properties.getProperty(key);
                result.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
