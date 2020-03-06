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
    //取整数和两位小数
    private final static String  TWO_DECIMAL_FORMAT="0.00";
    //以百分比方式计数，并取三位小数     稳定性五九标准：99.999%
    private final static String  TWO_DECIMAL_BAI_FORMAT="#.###%";
    //每三位以逗号进行分隔。   美元一般是用这种格式： 本月支出$1,000,000
    private final static String  USA_FORMAT=",###";
    //显示为科学计数法，并取五位小数
    private final static String  SCIENTIFIC_FORMAT="#.#####E0";

    private NumberUtil() {
    }

    /**
     * 根据正则格式化数字
     * @param data
     * @return
     */
    public static String getNumString(Double data,String pattern){
        return new DecimalFormat(pattern).format(data);
    }


    public static void main(String[] args) {
        String result=getNumString(123.3323423,NumberUtil.TWO_DECIMAL_FORMAT);
        System.out.println(result);
    }

}
