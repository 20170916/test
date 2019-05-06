package com.lo.string;

import com.lo.util.StringUtil;
import org.junit.Test;

public class RegexTest {

    private static final String CONTINUOUS_CHAR="(/)\\1+";
    @Test
    public void test(){
        String str="//a///b////c/d";
        System.out.println(str.replaceAll(CONTINUOUS_CHAR,"/"));
    }

    @Test
    public void testMergeContinuousChar(){
        String str="//a///b////c/d";
        System.out.println(StringUtil.mergeContinuousChar(str,"/"));
    }
}
