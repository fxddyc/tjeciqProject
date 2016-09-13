package cn.com.eship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by simon on 16/9/13.
 */
@Controller
@RequestMapping("/dataWarehouse")
public class DataWarehouseController {
    /**
     * 跳转到数据仓库首页
     *
     * @return
     */
    @RequestMapping("/toDatawarehousePage")
    public String toDatawarehousePage() {
        return "datawareHourseList";
    }

}
