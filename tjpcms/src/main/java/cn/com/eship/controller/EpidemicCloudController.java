package cn.com.eship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by simon on 16/7/14.
 */
@Controller
@RequestMapping("epidemicCloud")
public class EpidemicCloudController {
    @RequestMapping("epidemicCloudPage")
    public String epidemicCloudPage() {
        return "epidemicCloud";
    }
}
