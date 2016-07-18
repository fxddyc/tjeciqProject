package cn.com.eship.service;

import cn.com.eship.model.Baidubaike;

import java.util.List;

/**
 * Created by simon on 16/7/15.
 */
public interface EpidemicBaikeService {
    public List<Baidubaike> findAllepidemicBaike() throws Exception;

    public String makeBaikeInfoJson(String id) throws Exception;
}
