package cn.com.eship.service.impl;

import cn.com.eship.dao.EpidemicDao;
import cn.com.eship.service.CommonService;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by simon on 16/9/19.
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private EpidemicDao epidemicDao;

    @Override
    public String makeEpidemicNameListJson() throws Exception {
        return new ObjectMapper().writeValueAsString(epidemicDao.findEpidemicNameList1());
    }

    @Override
    public String makeRegionListJson() throws Exception {
        return new ObjectMapper().writeValueAsString(epidemicDao.findEpidemicRegionList1());
    }
}
