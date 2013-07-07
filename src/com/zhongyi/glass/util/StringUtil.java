package com.zhongyi.glass.util;

public class StringUtil {
    /**
     * 判断是否相等
     * 
     * @param a
     * @param b
     * @return
     */
    public static boolean equals(String a, String b) {
        if (a != null) {
            return a.equals(b);
        } else {
            return b == null;
        }
    }

    /**
     * 判断是否相等<br />
     * null与空String视为相等
     * 
     * @param a
     * @param b
     * @return
     */
    public static boolean equalsIgnorEmpty(String a, String b) {
        if (isEmpty(a) && isEmpty(b)) {
            return true;
        }
        if (a != null) {
            return a.equals(b);
        } else {
            return b == null;
        }
    }

    /**
     * toString<br />
     * null被转换为空String
     * 
     * @param a
     * @return
     */
    public static String toStringIgnorNull(Object a) {
        if (a == null) {
            return "";
        } else {
            return String.valueOf(a);
        }
    }

    /**
     * 判断是否为null或空String
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 判断是否为null或空String或全部为空白符
     * 
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 转换为int<br />
     * 如果转换失败返回-1
     * 
     * @param id
     * @return
     */
    public static int getInt(String id) {
        if (id == null) {
            return -1;
        }
        int idi = 0;
        try {
            idi = Integer.parseInt(id, 10);
        } catch (NumberFormatException e) {
            return -1;
        }
        return idi;
    }

    /**
     * 转换为long<br />
     * 如果转换失败返回-1
     * 
     * @param id
     * @return
     */
    public static long getLong(String id) {
        if (id == null) {
            return -1;
        }
        long idi = 0;
        try {
            idi = Long.parseLong(id, 10);
        } catch (NumberFormatException e) {
            return -1;
        }
        return idi;
    }

    public static String getSystemEolStyle() {
        String eol = System.getProperty("line.separator");
        if (eol == null) {
            return null;
        }
        if (eol.length() > 1) {
            return "CRLF";
        }
        if ("\r".equals(eol)) {
            return "CR";
        }
        if ("\n".equals(eol)) {
            return "LF";
        }
        return null;
    }

    public static String valueOf(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }

    public static String trim(String str) {
        return str == null ? "" : str.trim();
    }

    public static String emptyStrToNull(String param) {
        if (isEmpty(param)) {
            return null;
        }

        return param;
    }
}
