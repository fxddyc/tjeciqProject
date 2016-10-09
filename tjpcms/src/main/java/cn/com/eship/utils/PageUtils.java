package cn.com.eship.utils;

/**
 * Created by simon on 16/7/17.
 */
public class PageUtils {
    public static int getFirstPosition(Integer pageNo) {
        int result = 0;
        if (pageNo == null) {
            return 0;
        } else {
            result = (pageNo) * 10;
        }
        return result < 0?0:result;
    }
}
