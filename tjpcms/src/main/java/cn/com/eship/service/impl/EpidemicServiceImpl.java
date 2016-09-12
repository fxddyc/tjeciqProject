package cn.com.eship.service.impl;

import cn.com.eship.dao.EpidemicAppearDao;
import cn.com.eship.dao.EpidemicDao;
import cn.com.eship.model.EpidemicAppear;
import cn.com.eship.service.EpidemicService;
import cn.com.eship.utils.PageUtils;
import cn.com.eship.utils.TimeUtils;
import com.sun.deploy.net.proxy.pac.PACFunctions;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by simon on 16/7/17.
 */
@Service
public class EpidemicServiceImpl implements EpidemicService {
    @Autowired
    private EpidemicAppearDao epidemicAppearDao;
    @Autowired
    private EpidemicDao epidemicDao;

    /**
     * 疫情综合查询
     *
     * @param pageNo
     * @param epidemicName
     * @param regionCn
     * @param startDate
     * @param endDate
     * @return
     * @throws Exception
     */
    @Override
    public String makeEpidemicAppearListJson(String pageNo, String epidemicName, String regionCn, String startDate, String endDate) throws Exception {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        Map<String, Object> parameMap = new HashMap<String, Object>();
        parameMap.put("pageNo", PageUtils.getFirstPosition(StringUtils.isNotBlank(pageNo) ? Integer.parseInt(pageNo) : 0));

        if (StringUtils.isNotBlank(epidemicName)) {
            parameMap.put("epidemicName", epidemicName);
        }
        if (StringUtils.isNotBlank(regionCn)) {
            parameMap.put("regionCn", regionCn);
        }
        if (StringUtils.isNotBlank(startDate)) {
            parameMap.put("startDate", TimeUtils.convertToDateString(startDate));
        }
        if (StringUtils.isNotBlank(endDate)) {
            parameMap.put("endDate", TimeUtils.convertToDateString(endDate));
        }
        //TODO 坑2 parameMap
        List<EpidemicAppear> epidemicAppearList = epidemicAppearDao.findEpidemicAppearListByCondition(parameMap);
        if (epidemicAppearList != null && epidemicAppearList.size() > 0) {
            jsonMap.put("epidemicAppearList", epidemicAppearList);
            //TODO 坑1
            jsonMap.put("epidemicAppearListCount", epidemicAppearDao.findEpidemicAppearList(parameMap).size());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return objectMapper.writeValueAsString(jsonMap);
    }

    @Override
    public List<Object> getEpidemicNameList(String keyword) throws Exception {
        List<Object> epidemicNameList = epidemicDao.findEpidemicNameList(keyword);
        return epidemicNameList;
    }

    @Override
    public List<Object> getEpidemicRegionList(String keyword) throws Exception {
        List<Object> epidemicNameList = epidemicDao.findEpidemicRegionList(keyword);
        return epidemicNameList;
    }

    @Override
    public EpidemicAppear getEpidemicAppearById(String id) throws Exception {
        return epidemicAppearDao.findEpidemicAppearById(id);
    }


}
