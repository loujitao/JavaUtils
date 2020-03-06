package com.steve.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @Description: TODO
 * @Author: SteveTao
 * @Date: 2019/8/1911:25
 **/
public class DateUtil {

    private static final String hhmmFormat="HH:mm";
    private static final String MMddFormat="MM-dd";
    private static final String yyyyFormat="yyyy";
    private static final String yyyyChineseFormat="yyyy年";
    private static final String yyyyMMddFormat="yyyy-MM-dd";
    private static final String fullFormat="yyyy-MM-dd HH:mm:ss";
    private static final String MMddChineseFormat="MM月dd日";
    private static final String yyyyMMddChineseFormat="yyyy年MM月dd日";
    private static final String fullChineseFormat="yyyy年MM月dd日 HH时mm分ss秒";
    private static final String [] WEEKS={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};

    public static void main(String[] args) {
        String yestoday=getPastDateString(new Date(),1);
        System.out.println(yestoday);
    }

    private DateUtil() {
    }

    /**
     * 得到指定时间的时间日期格式字符串
     * @param date 指定的时间
     * @param format 时间日期格式
     * @return
     */
     public static String getFormatDateTime(Date date, String format){
          DateFormat df=new SimpleDateFormat(format);
            return df.format(date);
       }
    /**
     * 按照日期格式正则，转化字符串为日期类型数据
     * @param dateString
     * @param pattern
     * @return
     * @throws Exception
     */
     public static Date parserDate(String dateString, String pattern) throws Exception {
         SimpleDateFormat sdf=new SimpleDateFormat(pattern);
         return sdf.parse(dateString);
     }

    /**
    * 判断是否是润年
    * @param date 指定的时间
    * @return true:是润年,false:不是润年
    */
     public static boolean isLeapYear(Date date) {
         Calendar cal= Calendar.getInstance();
         cal.setTime(date);
         return isLeapYear(cal.get(Calendar.YEAR));
      }
    /**
    * 判断是否是润年
    * @return true:是润年,false:不是润年
    */
     public static boolean isLeapYear(int year) {
           GregorianCalendar calendar = new GregorianCalendar();
           return calendar.isLeapYear(year);
      }
    /**
          * 求出指定的时间那天是星期几
          * @param date 指定的时间
          * @return 星期X
     */
     public static String getWeekString(Date date){
         return DateUtil.WEEKS[getWeek(date)-1];
     }
     /**
      * 求出指定时间那天是星期几
      * @param date 指定的时间
      * @return 1-7
    */
     public static int getWeek(Date date){
         int week=0;
         Calendar cal=Calendar.getInstance();
         cal.setTime(date);
         week=cal.get(Calendar.DAY_OF_WEEK);
         return week;
     }
    /**
    * 取得两时间相差的天数
    * @param from 第一个时间
    * @param to 第二个时间
    * @return 相差的天数
    */
     public static long getBetweenDays(Date from, Date to){
         long days=0;
         long dayTime=24*60*60*1000;
         long fromTime=from.getTime();
         long toTime=to.getTime();
         long times=Math.abs(fromTime-toTime);
         days=times/dayTime;
         return days;
     }
    /**
    * 取得两时间相差的小时数
    * @param from 第一个时间
    * @param to 第二个时间
    * @return 相差的小时数
    */
     public static long getBetweenHours(Date from,Date to){
         long hours=0;
         long hourTime=60*60*1000;
         long fromTime=from.getTime();
         long toTime=to.getTime();
         long times=Math.abs(fromTime-toTime);
         hours=times/hourTime;
         return hours;
     }
    /**
    * 取得在指定时间上加减days天后的时间
    * @param date 指定的时间
    * @param days 天数,正为加，负为减
    * @return 在指定时间上加减days天后的时间
    */
     public static Date addDays(Date date,int days){
         Date time=null;
         Calendar cal=Calendar.getInstance();
         cal.add(Calendar.DAY_OF_MONTH, days);
         time=cal.getTime();
         return time;
     }
    /**
    * 取得在指定时间上加减months月后的时间
    * @param date 指定时间
    * @param months 月数，正为加，负为减
    * @return 在指定时间上加减months月后的时间
    */
     public static Date addMonths(Date date,int months){
         Date time=null;
         Calendar cal=Calendar.getInstance();
         cal.add(Calendar.MONTH, months);
         time=cal.getTime();
        return time;
     }
    /**
    * 取得在指定时间上加减years年后的时间
    * @param date 指定时间
    * @param years 年数，正为加，负为减
    * @return 在指定时间上加减years年后的时间
    */
     public static Date addYears(Date date,int years){
         Date time=null;
         Calendar cal=Calendar.getInstance();
         cal.add(Calendar.YEAR, years);
         time=cal.getTime();
         return time;
     }

    /**
     * 获取某个日期，前几天的字符串格式：yyyy-MM-dd
     * @param date  某个时间点
     * @param days  过去天数
     * @return  yyyy-MM-dd字符串
     */
     public static String getPastDateString(Date date,int days){
         Calendar calendar=Calendar.getInstance();
         calendar.setTime(date);
         calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - days);
         Date today=calendar.getTime();
         return getFormatDateTime(today,DateUtil.yyyyMMddFormat);
     }

}
