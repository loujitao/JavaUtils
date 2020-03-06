package com.steve.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description: 配置文件读取类
 * @Author: SteveTao
 * @Date: 2020/3/617:17
 **/
public class PropertiesUtil {
    private static Properties props = null;

    private PropertiesUtil() {
    }

    public static boolean setConfigFilePath(String filePath){
        props = new Properties();
        boolean flag=false;
        try {
            if(filePath.isEmpty()){
                //默认加载资源文件价下的application.properties
                ClassLoader classLoader = PropertiesUtil.class.getClassLoader();
                InputStream resourceAsStream = classLoader.getResourceAsStream("/application.properties");
                props.load(resourceAsStream);
            }else {
                props.load(PropertiesUtil.class.getResourceAsStream(filePath));
            }
            flag=true;
        } catch (IOException e) {
            throw  new RuntimeException("找不到资源文件："+filePath);
        }
        return false;
    }

    public static void checkProps(){
        if(props==null){
            throw  new RuntimeException("请先指定需要读取的配置文件： 调用setConfigFilePath(String filePath)");
        }
    }

    public static void setProperty(String key, String value){
        checkProps();
        props.setProperty(key, value);
    }

    public static String getProperty(String key) {
        checkProps();
       return  props.getProperty(key);
    }

    public static void main(String[] args) {
        PropertiesUtil.setConfigFilePath("/db.properties");
        String str = PropertiesUtil.getProperty("mysql.name");
        System.out.println(str);
    }

}
