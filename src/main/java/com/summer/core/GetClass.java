package com.summer.core;


import com.summer.ioc.ClasspathPackageScanner;
import com.summer.utils.ConstantUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 获取相应类对象
 * IoC的解析
 */
public class GetClass {
    private static ClasspathPackageScanner classpathPackageScanner;

    public static List<Class> getClassList(String key)
            throws IOException, ClassNotFoundException {//key = scan.package
        classpathPackageScanner = new ClasspathPackageScanner(ConstantUtils.PROPERTY_MAP.get(key));
        List<String> list = classpathPackageScanner.getFullyQualifiedClassNameList();
        List<Class> classList = new ArrayList<Class>();
        for (String string : list) {
            classList.add(Class.forName(string));
        }
        return classList;
    }
}
