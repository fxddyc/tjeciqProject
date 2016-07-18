package cn.com.eship.service.impl;

import cn.com.eship.dao.EpidemicAppearDao;
import cn.com.eship.model.EpidemicAppear;
import cn.com.eship.service.EpidemicService;
import cn.com.eship.utils.PageUtils;
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

    @Override
    public String makeEpidemicAppearListJson(String pageNo) throws Exception {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        List<EpidemicAppear> epidemicAppearList = epidemicAppearDao.findEpidemicAppearList(PageUtils.getFirstPosition(StringUtils.isNotBlank(pageNo) ? Integer.parseInt(pageNo) : 0));
        if (epidemicAppearList != null && epidemicAppearList.size() > 0) {
            jsonMap.put("epidemicAppearList", epidemicAppearList);
            jsonMap.put("epidemicAppearListCount", epidemicAppearDao.findEpidemicAppearCount());
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return objectMapper.writeValueAsString(jsonMap);
    }
}
