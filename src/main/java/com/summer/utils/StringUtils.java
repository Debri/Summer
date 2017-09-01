package com.summer.utils;

import java.net.URL;

/**
 * Created by Liuqi
 *
 * @date: 2017/8/28.
 * @author: liuqi
 * @version: v1.0.0
 */
public class StringUtils {
    public static Boolean isEmpty(String str) {
        return (str == null || "".equals(str));
    }

    public static String trimWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
            sb.deleteCharAt(0);
        }
        while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }

    public static boolean hasLength(CharSequence str) {

        return (str != null && str.length() > 0);
    }

    /**
     * 将"." 转换为"/"
     *
     * @param input
     * @return
     */
    public static String dotToSplash(String input) {
        return input.replaceAll("\\.", "/");
    }

    /**
     * "file:/home/whf/cn/fh" -> "/home/whf/cn/fh"
     * "jar:file:/home/whf/foo.jar!cn/fh" -> "/home/whf/foo.jar"
     */
    public static String getRootPath(URL url) {
        String fileUrl = url.getFile();
        int pos = fileUrl.indexOf('!');

        if (-1 == pos) {
            return fileUrl;
        }
        return fileUrl.substring(5, pos);
    }

    public static String trimSuffix(String input) {
        int position = input.lastIndexOf(".");
        if (-1 != position) {
            return input.substring(0, position);
        }
        return input;
    }
}
