package com.steve.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: TODO
 * @Author: SteveTao
 * @Date: 2019/8/2010:11
 **/
public class StringUtil {

    /**
     * 不为空，null , "null"
     * @param str
     * @return
     */
    public static boolean checkString(String str){
        boolean fl=false;
        if(str!=null && !"".equals(str.trim()) && !"null".equals(str)){
            fl=true;
        }
        return fl;
    }
    /**
     * 判断字符是否为空串或NULL
     * @param s
     * @return
     */
    public static boolean isEmpty(String s){
        if(s == null || "".equals(s.trim())) return true;
        return false;
    }
    /**
     * 字符串空指针预处理
     * @param s
     * @return
     */
    public static String getString(String s){
        if(s == null){
            return "";
        }
        return s;
    }
    /**
     *字母开头，数字、字母、组成下划线
     *
     * */
    public static boolean checkEnAndNum(String str){
        String reg="^[a-zA-Z]+[a-zA-Z0-9_]*$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        boolean matches = m.matches();
        int i = str.length();
        if(matches && i<=40){
            return true;
        }else{
            return false;
        }
    }
    /**
     * 字母、数字、下划线
     * */
    public static boolean checkenNum(String str){
        String reg="^[A-Za-z0-9_]+$";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(str);
        boolean matches = m.matches();
        return matches;
    }


}
