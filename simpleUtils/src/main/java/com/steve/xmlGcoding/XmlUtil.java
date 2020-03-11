package com.steve.xmlGcoding;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: TODO
 * @Author: SteveTao
 * @Date: 2020/3/1116:14
 **/
public class XmlUtil {

    //分级打印各个节点   element为根节点的元素
    public static void printlnE(Element element, int i){
        if(element!=null) {
            Iterator iterator = element.elementIterator();
            //有子节点的为枝干节点不打印， 没有子节点的为叶子节点，打印它的text值
            if(iterator.hasNext()){
                System.out.println("===="+i+"====k: "+element.getName());
            }else {
                System.out.println("===="+i+"====k: "+element.getName()+" v:"+element.getText());
            }
            while (iterator.hasNext()){
                Element stuChild = (Element) iterator.next();
                printlnE(stuChild,i+1);
            }
        }
    }

    // 将xml的element转换为json对象  element为根节点的元素
    public static JSONObject xmlToJson(Element element){
        JSONObject jsonObj=new JSONObject();
        iterateNodes(element, jsonObj);
        return jsonObj;
    }
    @SuppressWarnings("unchecked")
    public static void iterateNodes(Element node, JSONObject json) {
        // 获取当前元素的名称
        String nodeName = node.getName();
        // 判断已遍历的JSON中是否已经有了该元素的名称
        if (json.containsKey(nodeName)) {
            // 该元素在同级下有多个
            Object Object = json.get(nodeName);
            JSONArray array = null;
            if (Object instanceof JSONArray) {
                array = (JSONArray) Object;
            } else {
                array = new JSONArray();
                array.add(Object);
            }
            // 获取该元素下所有子元素
            List<Element> listElement = node.elements();
            if (listElement.isEmpty()) {
                // 该元素无子元素，获取元素的值
                String nodeValue = node.getTextTrim();
                array.add(nodeValue);
                json.put(nodeName, array);
                return;
            }
            // 有子元素
            JSONObject newJson = new JSONObject();
            // 遍历所有子元素
            for (Element e : listElement) {
                // 递归
                iterateNodes(e, newJson);
            }
            array.add(newJson);
            json.put(nodeName, array);
            return;
        }
        // 该元素同级下第一次遍历
        // 获取该元素下所有子元素
        List<Element> listElement = node.elements();
        if (listElement.isEmpty()) {
            // 该元素无子元素，获取元素的值
            String nodeValue = node.getTextTrim();
            json.put(nodeName, nodeValue);
            return;
        }
        // 有子节点，新建一个JSONObject来存储该节点下子节点的值
        JSONObject object = new JSONObject();
        // 遍历所有一级子节点
        for (Element e : listElement) {
            // 递归
            iterateNodes(e, object);
        }
        json.put(nodeName, object);
        return;
    }


    //转化为对应的实体对象
    // 思路： xml <==>  jsonObJect <==>  实体对象
    public static <T> T jsonToEntity(JSONObject jsonObject, Class<T> clazz){
        return JSONObject.toJavaObject(jsonObject,clazz);
    }


    //=====================测试主类==========================
    public static void main(String[] args) throws Exception {
        //1.创建Reader对象
        SAXReader reader = new SAXReader();
        //2.加载xml
        Document document = reader.read(new File("E:\\ideawork\\myUtils\\simpleUtils\\src\\main\\resources\\taskInfo.xml"));
        //3.获取根节点
        Element rootElement = document.getRootElement();
        //=============测试各种方法
        //1）打印控制台方法
//        printlnE(rootElement,1);

        //2） 格式话为json字符串方法
        JSONObject jsonObject = xmlToJson(rootElement);
        System.out.println(jsonObject.toJSONString());

        //3）JSONObject转化为实体对象
        String person="{\"name\":\"zhangshan\",\"age\":23}";
        JSONObject jsonObject2 = JSON.parseObject(person);
        Person p = XmlUtil.jsonToEntity(jsonObject2, Person.class);
        System.out.println(p);
    }

 class Person{
    private String name;
    private int age;

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public int getAge() {
         return age;
     }

     public void setAge(int age) {
         this.age = age;
     }
 }
}