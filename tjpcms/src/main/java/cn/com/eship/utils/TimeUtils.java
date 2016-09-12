package cn.com.eship.utils;

import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;

/**
 * Created by simon on 16/9/12.
 */
public class TimeUtils {
    public static String convertToDateString(String date) throws Exception {
        return StringUtils.isNotBlank(date) ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("dd/MM/yyyy").parse(date)) : "";
    }
}
