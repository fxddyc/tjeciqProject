package cn.com.eship.service;

import java.util.List;
import java.util.Map;

/**
 * Created by guoji on 2017/7/28 0028.
 */
public interface TestService {
    Map<String, List> findMapListData(String mapDataInterval, String startDate, String endDate) throws Exception;
}
