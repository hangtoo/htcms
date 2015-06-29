
package common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Administrator
 * 日期-字符串工具辅助类
 */

public class DateUtil {
	
	/**
	 * 日期格式
	 */
	private static String pattern_d = "yyyy-MM-dd";

	private static String pattern_t = "HH:mm:ss";
	
	private static String pattern_full = "yyyy-MM-dd HH:mm:ss";
	
	private static String pattern_yyyyMM="yyyyMM";

	private static SimpleDateFormat shotDateFormat = new SimpleDateFormat(pattern_d);
	
	private static SimpleDateFormat shortTimeFormat = new SimpleDateFormat(pattern_t);

	private static SimpleDateFormat fullDateFormat = new SimpleDateFormat(pattern_full);
	
	private static SimpleDateFormat yyyyMMDateFormat = new SimpleDateFormat(pattern_yyyyMM);
	
	public static String NOWYYYYMM=DateUtil.DateToYyyyMM(new java.util.Date());

	/**
	 * 将所给字符串转换为Date对象
	 * 
	 * @param source
	 *            要转换的字符串
	 * @return 对应的Date对象
	 */
	public static Date StringToDate(String source) {
		Date date = new Date();
		try {
			date = fullDateFormat.parse(source);
		} catch (ParseException pe) {
			return null;
		}
		return date;
	}

	/**
	 * 将所给日期转换为时间(HH:mm:ss)字符串
	 * 
	 * @param source
	 *            要转换的日期对象
	 * @return 时间字符串
	 */
	public static String DateToTime(Date source) {
		return shortTimeFormat.format(source);
	}

	/**
	 * 将所给的Date对象转换为字符串
	 * 
	 * @param source
	 *            要转换的Date对象
	 * @return 目标字符串
	 */
	public static String DateToString(Date source) {
		
		if(source==null)
			return null;
		
		String result = "";
		result = fullDateFormat.format(source);
		return result;
	}
	
	public static String DateToYyyyMM(Date source){
		if(source==null)
			return null;
		String result = "";
		result = yyyyMMDateFormat.format(source);
		return result;
	}
	
	/**
	 * 将所给日期转换为时间(yy-MM-dd)字符串
	 * 
	 * @param source
	 *            要转换的日期对象
	 * @return 时间字符串
	 */
	public static String DateToStr(Date source) {
		String result = "";
		result = shotDateFormat.format(source);
		return result;
	}

	/**
	 * 日期加减操作
	 * 
	 * @param source
	 *            源日期
	 * @param field
	 *            项（日，月，年)
	 * @param num
	 *            数量 + 为加，-为减
	 * @return
	 */
	public static Date dateRoler(Date source, int field, int num) {
		Calendar c = Calendar.getInstance();
		c.setTime(source);
		c.add(field, num);
		return c.getTime();
	}

	/**
	 * 将字符串转化为date对象
	 * @param dateString 时间字符串
	 * @return date对象
	 */
	public static Date convertStringToDate(String dateString) {
		Date date = null;
		try {
			date = fullDateFormat.parse(dateString);
		} catch (Exception e) {
			date = null;
		}
		return date;
	}
	
	/**
	 * 将字符串转化为date对象
	 * @param dateString 时间字符串
	 * @return date对象
	 */
	public static Date convertShortStringToDate(String dateString) {
		Date date = null;
		try {
			date = shotDateFormat.parse(dateString);
		} catch (Exception e) {
			date = null;
		}
		return date;
	}
	
	 /**
     * 获得短日期格式
     * @param date
     * @return
     */
    @SuppressWarnings("deprecation")
	public static String convertDateToShortString(Date date){
        String datestring = "";
        int year = 0,month = 0,day = 0;
      
        if(date==null) return datestring;
        
        year = date.getYear()+1900;
        month = date.getMonth()+1;
        day = date.getDate();
        datestring = year+"-";
        if(month>9){
            datestring += month+"-";
        }else{
            datestring += "0"+month+"-";            
        }
        if(day>9){
            datestring += day;
        }else{
            datestring += "0"+day;            
        }        
        return datestring;
    }
    
    /**
     * 将短日期字符串转为长日期最小字符串
     * @param datestr 2006-10-10
     * @return 长日期最小 2006-10-10 00:00:00
     */
	public static String getMinDateStr(String dateStr){
		dateStr += " 00:00:00";
		return dateStr;
	}
	
	/**
	 * 将短日期字符串转为长日期最大的字符串
	 * @param dateStr 短日期 如:2006-10-10
	 * @return 长日期最大 如：2006-10-10 23:59:59
	 */
	public static String getMaxDateStr(String dateStr){
		dateStr += " 23:59:59";
		return dateStr;
	}
	
    //	得到当前时间
     public static String getDateNow()
     {	
	    return shotDateFormat.format(new java.util.Date());	
     } 
	
     //	得到当前时间
     public static String getTimeNow()
     {	
	    return fullDateFormat.format(new java.util.Date());	
     } 
     
     //1~7分别表示周一到周日
 	public static int getWeekday(Date date){
        Calendar gc = Calendar.getInstance();
        gc.setTime(date);
        int week=gc.get(Calendar.DAY_OF_WEEK);//"","星期日","星期一","星期二","星期三","星期四","星期五","星期六"
        week--;
		if(week==0){
			week=7;
		}
		
		return week;
	}
 	
    /**
     * 日期加N天
     * @param Sring 时间
     * @return 加后的日期
     */
	public static Date addDay(Date date, int m, int d) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			Calendar cd = Calendar.getInstance();
			cd.setTime(date);
			cd.add(Calendar.DATE, d);// 添加一天
			cd.add(Calendar.MONTH, m);//添加一个月

			return cd.getTime();

		} catch (Exception e) {
			return null;
		}
	}
}
