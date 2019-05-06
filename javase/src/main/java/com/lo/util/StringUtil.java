package com.lo.util;

public class StringUtil {

    private static final String CONTINUOUS_CHAR="(continuous_char)\\1+";

    /**
     * 合并多个连续相同的字符为一个字符
     * @param str
     * @param merge
     * @return
     */
    public static String mergeContinuousChar(String str,String merge){
        String regex=CONTINUOUS_CHAR.replace("continuous_char",merge);
        return str.replaceAll(regex,merge);
    }
}
