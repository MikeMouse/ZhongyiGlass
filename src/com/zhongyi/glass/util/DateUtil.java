package com.zhongyi.glass.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期Util
 *
 * @author liqianxi
 * @date 2013-06-29
 *
 */
public class DateUtil {

    /**
     * 日期格式：YYYYMMDD
     */
    public static String DATE_FORMATE_YYYYMMDD_DEFAULT = "yyyyMMdd";

    /**
     * 日期格式：YYYYMM
     */
    public static String DATE_FORMATE_YYYYMM_DEFAULT = "yyyyMM";

    /**
     * 日期格式：YYYY-MM-DD
     */
    public static String DATE_FORMATE_YYYYMMDD_WITH_HYPHEN = "yyyy-MM-dd";

    /**
     * 日期格式：YYYY-MM
     */
    public static String DATE_FORMATE_YYYYMM_WITH_HYPHEN = "yyyy-MM";

    /**
     * 日期格式：YYYY.MM.DD HH:MI:SS
     */
    public static String DATE_FORMATE_YYYYMMDDHHMISS_WITH_DOT = "yyyy.MM.dd HH:mm:ss";

    /**
     * 将日期转换为日期字符串
     * 
     * @param date 日期
     * @return 日期字符串
     */
    public static String convertDateToDateStr(Date date) {
        if (date == null) {
            return null;
        }

        return new SimpleDateFormat(DATE_FORMATE_YYYYMMDD_DEFAULT).format(date);
    }

    /**
     * 将日期转换为日期字符串
     * 
     * @param date 日期
     * @param format 日期格式
     * @return 日期字符串
     */
    public static String convertDateToDateStr(Date date, String format) {
        if (date == null) {
            return null;
        }

        if (StringUtil.isBlank(format)) {
            return convertDateToDateStr(date);
        }

        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 将日期字符串转换为日期
     * 
     * @param dateStr 日期字符串
     * @return
     */
    public static Date convertDateStrToDate(String dateStr) {
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }

        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMATE_YYYYMMDD_DEFAULT);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将日期字符串转换为日期
     * 
     * @param dateStr 日期字符串
     * @param format 日期格式
     * @return
     */
    public static Date convertDateStrToDate(String dateStr, String format) {
        if (StringUtil.isEmpty(dateStr)) {
            return null;
        }
        if (StringUtil.isBlank(format)) {
            return convertDateStrToDate(dateStr);
        }

        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将日期字符串转换为另一种日期格式
     * 
     * @param dateStr 日期字符串
     * @param formatOld 转换前日期格式
     * @param formatNew 转换后日期格式
     * @return 格式转换后的日期
     */
    public static String convertDateStrFormat(String dateStr, String formatOld, String formatNew) {
        if (StringUtil.isEmpty(dateStr) || StringUtil.isEmpty(formatOld) || StringUtil.isEmpty(formatNew)) {
            return dateStr;
        }

        return convertDateToDateStr(convertDateStrToDate(dateStr, formatOld), formatNew);
    }

    /**
     * 将日期字符串转换为时间戳(秒级)
     * 
     * @param dateStr 日期字符串
     * @param format 日期格式
     * @return
     */
    public static long convertDateStrToTimeStamp(String dateStr) {
        if (StringUtil.isEmpty(dateStr)) {
            return -1L;
        }

        Date result = convertDateStrToDate(dateStr);
        return result == null ? -1L : (result.getTime() / 1000L);
    }

    /**
     * 将日期字符串转换为时间戳(秒级)
     * 
     * @param dateStr 日期字符串
     * @param format 日期格式
     * @return
     */
    public static long convertDateStrToTimeStamp(String dateStr, String format) {
        if (StringUtil.isEmpty(dateStr)) {
            return -1L;
        }

        if (StringUtil.isBlank(format)) {
            return convertDateStrToTimeStamp(dateStr);
        }

        Date result = convertDateStrToDate(dateStr, format);
        return result == null ? -1L : (result.getTime() / 1000L);
    }

    /**
     * 将时间戳(秒级)转换为日期字符串
     * 
     * @param timeStamp 时间戳(秒级)
     * @param format 日期格式
     * @return 日期字符串
     */
    public static String convertTimestampToDateStr(long timeStamp, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeStamp * 1000L);
        return convertDateToDateStr(calendar.getTime(), format);
    }

    /**
     * 取得指定日期下一日的时间戳(秒级)
     * 
     * @param dateStr 日期字符串
     * @param format 日期格式
     * @return 指定日期下一日的时间戳
     */
    public static long getNextDayTimestamp(String dateStr, String format) {
        Date date = convertDateStrToDate(dateStr, format);
        if (date == null) {
            return -1L;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTimeInMillis() / 1000L;
    }

    /**
     * 取得当前月份第一天
     * 
     * @param format 日期格式
     * @return 当前月份第一天
     */
    public static String getFirstDayOfMonth(String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return convertDateToDateStr(calendar.getTime(), format);
    }

    /**
     * 取得前n天日期
     * 
     * @param n 前n天
     * @param format 日期格式
     * @return 前n天日期
     */
    public static String getNDaysBefore(int n, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, n);

        return convertDateToDateStr(calendar.getTime(), format);
    }

    /**
     * 取得今日
     * 
     * @param format 日期格式
     * @return 今日
     */
    public static String getCurrentDateStr(String format) {
        return getNDaysBefore(0, format);
    }

    /**
     * 取得昨日
     * 
     * @param format 日期格式
     * @return 昨日
     */
    public static String getYesterdayStr(String format) {
        return getNDaysBefore(-1, format);
    }

    /**
     * 重新格式化日期
     * @param formatSrc 源格式
     * @param formatDst 目标格式
     * @param date 需要格式化的日期
     * @return 格式后的日期
     */
    public static final String reFormatDate(String formatSrc, String formatDst, String date) {
        String target = "";
        DateFormat format1 = new SimpleDateFormat(formatSrc);
        DateFormat format2 = new SimpleDateFormat(formatDst);
        try {
            target = format2.format(format1.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return target;
    }
}
