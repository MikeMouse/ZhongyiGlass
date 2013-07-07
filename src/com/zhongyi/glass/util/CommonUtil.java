package com.zhongyi.glass.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

    /**
     * 日期格式：yyyyMMdd
     */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    public static final SimpleDateFormat YYYYMMDD_FORMATER = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD);

    /**
     * 日期格式：yyyy-MM-dd
     */
    public static final String DATE_FORMAT_YYYYMMDD_WITH_HYPHEN = "yyyy-MM-dd";

    /**
     * 年月格式：yyyyMM
     */
    public static final String DATE_FORMAT_YYYYMM = "yyyyMM";

    /**
     * 年月格式：yyyy-MM
     */
    public static final String DATE_FORMAT_YYYYMM_WITH_HYPHEN = "yyyy-MM";

    public static final SimpleDateFormat YYYYMMDD_WITH_HYPHEN_FORMATER = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD_WITH_HYPHEN);

    /**
     * 日期格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    public static final SimpleDateFormat YYYYMMDDHHMMSS_FORMATER = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMMSS);

    private static final String DECIMAL_PATTERN = "^[0-9]+(.[0-9]{0,2})?$";// 判断小数点后二位的数字的正则表达式
    private static final String NUMERAL_PATTERN = "^[0-9]*$";// 判断数字的正则表达式
    private static final String DATE_PATTERN = "^((\\d{2}(([02468][048])|([13579][26]))"
            + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
            + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
            + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
            + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
            + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";

    /**
     * 获取时间戳(精确到秒)
     * 
     * @return 时间戳(精确到秒)
     */
    public static final int millisToSecond() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * format时间戳(精确到秒)
     * 
     * @param second
     *            时间戳(精确到秒)
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static final String secondFormat(int second) {
        long mill = second * 1000L;
        return formatDate(mill, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * format时间戳(精确到日)
     * 
     * @param second
     *            时间戳(精确到日)
     * @return yyyy-MM-dd
     */
    public static final String dayFormat(int second) {
        long mill = second * 1000L;
        return formatDate(mill, DATE_FORMAT_YYYYMMDD_WITH_HYPHEN);
    }

    /**
     * 按yyyyMMdd获取int
     * @param mill
     * @return
     */
    public static final int getyyyyMMMddInt(long mill){
        return Integer.parseInt(formatDate(mill, DATE_FORMAT_YYYYMMDD));
    }

    /**
     * 日期格式化：将YYYYMMDD格式为yyyy-MM-dd
     * 
     * @param yyyyMMdd 需要格式化的日期
     * @return String 格式后的日期
     * @throws ParseException
     */
    public static final String formatyyyyMMMddToDash(String yyyyMMdd) {
        String target = "";
        DateFormat format1 = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD);
        DateFormat format2 = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD_WITH_HYPHEN);
        try {
            target = format2.format(format1.parse(yyyyMMdd));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return target;

    }

    /**
     * date format
     * @param mill
     * @param formatString
     * @return
     */
    private static final String formatDate(long mill, String formatString){
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        return sdf.format(new Date(mill));
    }

    /**
     * 圆转换为分
     * 
     * @param yuan
     * @return
     */
    public static final String yuanToFen(String yuan) {
        int index = yuan.indexOf(".");
        int length = yuan.length();
        Long amLong = 0l;
        if (index == -1) {
            amLong = Long.valueOf(yuan + "00");
        } else if (length - index >= 3) {
            amLong = Long.valueOf((yuan.substring(0, index + 3)).replace(".",
                    ""));
        } else if (length - index == 2) {
            amLong = Long.valueOf((yuan.substring(0, index + 2)).replace(".",
                    "") + 0);
        } else {
            amLong = Long.valueOf((yuan.substring(0, index + 1)).replace(".",
                    "") + "00");
        }
        return amLong.toString();
    }

    /**
     * 分转换为圆
     * 
     * @param fen
     * @return
     */
    public static final String fenToYuan(long fen) {
        return BigDecimal.valueOf(fen).divide(new BigDecimal(100)).toString();
    }

    /**
     * 验证是不是数字(验证到小数点后二位)
     * 
     * @param number
     * @return
     */
    public static boolean isDecimalNumber(String number) {
        return match(DECIMAL_PATTERN, number);
    }

    /**
     * 验证是不是数字(没有小数点)
     * 
     * @param number
     * @return
     */
    public static boolean isNumeral(String number) {
        return match(NUMERAL_PATTERN, number);
    }

    /**
     * 验证是不是日期
     * 
     * @param strDate
     * @return
     */
    public static boolean isDate(String strDate) {
        return match(DATE_PATTERN, strDate);
    }

    /**
     * 执行正则表达式
     * 
     * @param pattern
     *            表达式
     * @param str
     *            待验证字符串
     * @return 返回 <b>true </b>,否则为 <b>false </b>
     */
    private static boolean match(String pattern, String str) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 格式化页面显示金额
     * 
     * @param param
     * @return 格式化后的值
     */
    public static String formatDisplayMoney(long param) {
        if (param < 0) {
            return "0";
        }

        BigDecimal result = BigDecimal.valueOf(param).divide(BigDecimal.valueOf(100), 0, RoundingMode.DOWN);
        return result.toPlainString();
    }

    /**
     * 格式化存储在数据库的金额
     * 
     * @param param
     * @return 格式化后的值
     */
    public static String formatStoreMoney(String param) {
        if (StringUtil.isEmpty(param)) {
            return param;
        }

        BigDecimal result = BigDecimal.valueOf(StringUtil.getLong(param)).multiply(BigDecimal.valueOf(100));
        return result.toPlainString();
    }
}
