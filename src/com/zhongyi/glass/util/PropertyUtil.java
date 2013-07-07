package com.zhongyi.glass.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * property管理类
 * 
 * @author liqianxi
 * @date 2013-06-29
 * 
 */
public class PropertyUtil {
    private static Logger logger = Logger.getLogger(PropertyUtil.class);
    private static Properties config;

    /**
     * 配置property
     * 
     * @param config
     */
    public static synchronized void configure(Properties config) {
        PropertyUtil.config = config;
    }

    /**
     * 取得property
     * 
     * @param key
     * @return
     */
    public static String get(String key) {
        return (String) config.get(key);
    }

    /**
     * 注销旧的配置信息，并释放与之有关的资源
     */
    public static synchronized void distory() {
    }

    /**
     * 更新配置文件
     */
    public static synchronized boolean refreshConfig() {
        Properties conf = new Properties();
        InputStream in = null;
        try {
            in = PropertyUtil.class.getClassLoader().getResourceAsStream(
                    "config.properties");
            conf.load(new InputStreamReader(in, "UTF-8"));
        } catch (Exception e) {
            conf = null;
            logger.error("refresh config failed", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    logger.error("refresh config failed", e);
                }
            }
        }
        if (conf != null) {
            distory();
            configure(conf);
        }
        return conf != null;
    }
}
