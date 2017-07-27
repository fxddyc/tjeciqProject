package cn.com.eship.utils;

import java.util.UUID;

/**
 * Created by guoji on 2017/7/21 0021.
 */
public class CommenUtils {

    public static Boolean compareString(String str1,String str2){
        if(str1 !=null && str2 != null){
            String s1 = str1.replaceAll("\\pP|\\pS|\\pZ","").toLowerCase();
            String s2 = str2.replaceAll("\\pP|\\pS|\\pZ","").toLowerCase();
            if (s1.equals(s2)){
                return true;
            }else return false;
        }else return false;
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString().replace("-", "");
        return str;
    }
}
