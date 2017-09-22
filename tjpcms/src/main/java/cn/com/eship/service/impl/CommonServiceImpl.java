package cn.com.eship.service.impl;

import cn.com.eship.dao.EpidemicDao;
import cn.com.eship.dao.KindDicDao;
import cn.com.eship.dao.OIEDiseasesEntityDao;
import cn.com.eship.dao.WordsDao;
import cn.com.eship.model.KindDic;
import cn.com.eship.model.OieDiseasesEntity;
import cn.com.eship.service.CommonService;
import org.apache.htrace.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by simon on 16/9/19.
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private EpidemicDao epidemicDao;
    @Autowired
    private WordsDao wordsDao;
    @Autowired
    private KindDicDao kindDicDao;
    @Autowired
    private OIEDiseasesEntityDao oieDiseasesEntityDao;

    @Override
    public String makeEpidemicNameListJson() throws Exception {
        return new ObjectMapper().writeValueAsString(epidemicDao.findEpidemicNameList1());
    }

    @Override
    public String makeRegionListJson() throws Exception {
        return new ObjectMapper().writeValueAsString(epidemicDao.findEpidemicRegionList1());
    }

    @Override
    public String makekindWordsListJson() throws Exception {
        List<Map<String, String>> jsonList = new ArrayList<Map<String, String>>();
        List<KindDic> kindDicList = kindDicDao.findAllKindDicList();
        Map<String, String> map = null;
        for (KindDic kindDic : kindDicList) {
            map = new HashMap<String, String>();
            map.put("id", kindDic.getId());
            map.put("kindName", kindDic.getKindName());
            jsonList.add(map);
        }
        return new ObjectMapper().writeValueAsString(jsonList);
    }

    @Override
    public List<OieDiseasesEntity> findOieDiseasesEntityList(OieDiseasesEntity oieDiseasesEntity) throws Exception {
        return oieDiseasesEntityDao.findOieDiseasesEntityList(oieDiseasesEntity);
    }
}
