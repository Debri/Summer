package com.summer.utils;

import java.util.Map;

/**
 * Created by Liuqi on 2017/9/1
 *
 * @description: 常量
 * @author: debri_liu@163.com
 * @date: 2017/9/1.
 * @since: v1.0.0
 */
public class ConstantUtils {
    private static final String PROPERTY_PATH = "/scan-package.properties";
    public static final Map<String, String> PROPERTY_MAP = PropertiesUtils.readProperties(PROPERTY_PATH);
}
