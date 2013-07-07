package com.zhongyi.glass.constant;

/**
 * @author liqianxi
 * @date 2013-06-29
 */
public interface CodeConstant {
    // Session
    /** SESSION KEY : 运营帐号ID */
    public static final String SESSION_KEY_OPERATOR = "operatorid";
    /** SESSION KEY : 登录用户名 */
    public static final String SESSION_KEY_USER_NAME = "username";
    /** SESSION KEY : 登录用户 */
    public static final String SESSION_KEY_USER = "user";

    /** 性别：女 */
    public static final String GENDER_FEMALE = "0";
    /** 性别：男 */
    public static final String GENDER_MALE = "1";
    /** 性别（显示用）：女 */
    public static final String GENDER_DISPLAY_FEMALE = "女";
    /** 性别（显示用）：男 */
    public static final String GENDER_DISPLAY_MALE = "男";

    /** 状态：无效 */
    public static final String STATUS_INVALID = "0";
    /** 状态：有效 */
    public static final String STATUS_VALID = "1";

    /** 折扣类型：去零头 */
    public static final String DISCOUNT_TYPE_LINGTOU = "0";
    /** 折扣类型：打折 */
    public static final String DISCOUNT_TYPE_DAZHE = "1";

    /** 折扣类型名称：去零头 */
    public static final String DISCOUNT_TYPE_NAME_LINGTOU = "去零头";
    /** 折扣类型名称：打折 */
    public static final String DISCOUNT_TYPE_NAME_DAZHE = "打折";

    /** 眼镜类型：架镜 */
    public static final String GLASS_TYPE_JIAJING = "0";
    /** 眼镜类型：隐形 */
    public static final String GLASS_TYPE_YINXING = "1";

    /** 眼镜类型名称：架镜 */
    public static final String GLASS_TYPE_NAME_JIAJING = "架镜";
    /** 眼镜类型名称：隐形 */
    public static final String GLASS_TYPE_NAME_YINXING = "隐形";

    /** 眼镜状态：等待加工 */
    public static final String GLASS_STATUS_PROCESS = "0";
    /** 眼镜状态：等待取镜 */
    public static final String GLASS_STATUS_WAIT = "1";
    /** 眼镜状态：已取镜 */
    public static final String GLASS_STATUS_TAKEN = "2";

    /** 眼镜状态：等待加工 */
    public static final String GLASS_STATUS_NAME_PROCESS = "等待加工";
    /** 眼镜状态：等待取镜 */
    public static final String GLASS_STATUS_NAME_WAIT = "等待取镜";
    /** 眼镜状态：已取镜 */
    public static final String GLASS_STATUS_NAME_TAKEN = "已取镜";

    /** 页面操作类型：查询 */
    public static final String OPERATE_TYPE_COMMON_SEARCH = "0";
    /** 页面操作类型：分页 */
    public static final String OPERATE_TYPE_COMMON_PAGE = "1";
    /** 页面操作类型：编辑 */
    public static final String OPERATE_TYPE_COMMON_EDIT = "2";
}
