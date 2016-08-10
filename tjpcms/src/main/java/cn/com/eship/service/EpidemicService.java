package cn.com.eship.service;

import cn.com.eship.model.EpidemicAppear;

import java.util.List;

/**
 * Created by simon on 16/7/17.
 */
public interface EpidemicService {
    public String makeEpidemicAppearListJson(String pageNo) throws Exception;
    public List<Object> getEpidemicNameList(String keyword) throws Exception;
    public List<Object> getEpidemicRegionList(String keyword) throws Exception;
}
