package com.steve.number;

import java.text.DecimalFormat;

/**
 * @Description: TODO
 * @Author: SteveTao
 * @Date: 2019/8/1911:45
 **/
public class NumberUtil {

    //取所有整数部分
    private final static String  INT_FORMAT="#";
    //取一位整数和两位小数
    private final static String  TWO_DECIMAL_FORMAT="0.00";
    //以百分比方式计数，并取两位小数
    private final static String  TWO_DECIMAL_BAI_FORMAT="#.##%";
    //每三位以逗号进行分隔。
    private final static String  USA_FORMAT=",###";
    //显示为科学计数法，并取五位小数
    private final static String  SCIENTIFIC_FORMAT="#.#####E0";



    /**
     * 根据正则格式化数字
     * @param data
     * @return
     */
    public static String getInt(Double data,String pattern){
        return new DecimalFormat(pattern).format(data);
    }

    public static void main(String[] args) {
        String result=getInt(123.3323423,NumberUtil.TWO_DECIMAL_FORMAT);
        System.out.println(result);
    }

}