package cn.com.eship.service;

import cn.com.eship.model.EpidemicAppear;

/**
 * Created by simon on 16/7/14.
 */
public interface IndexService {
    /**
     * 疫情TOP10
     * @return
     */
    public String makeEpidemicTopTenJson(EpidemicAppear epidemicAppear) throws Exception;


    public String makeEpidemicLocalTopTenJson(EpidemicAppear epidemicAppear) throws Exception;
}
