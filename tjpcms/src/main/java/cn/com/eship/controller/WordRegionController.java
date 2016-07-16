package cn.com.eship.controller;

import cn.com.eship.service.WordRegionService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

/**
 * Created by simon on 16/7/15.
 */
@Controller
@RequestMapping("wordRegion")
public class WordRegionController {
    private final Logger logger = Logger.getLogger(WordRegionController.class);
    @Autowired
    private WordRegionService wordRegionService;

    @RequestMapping("wordRegionPage")
    public String wordRegionPage() {
        return "wordRegion";
    }

    @RequestMapping("wordRegion")
    public void wordRegion(String startDate, String endDate, String epidemicName, HttpServletResponse response) throws Exception {
            String json = wordRegionService.makeWordRegionJson(StringUtils.isNotBlank(startDate) ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("dd/MM/yyyy").parse(startDate)) : "", StringUtils.isNotBlank(startDate) ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new SimpleDateFormat("dd/MM/yyyy").parse(endDate)) : "",StringUtils.isNotBlank(epidemicName)?epidemicName:"霍乱");
            response.getOutputStream().write(json.getBytes("utf-8"));
    }

}
