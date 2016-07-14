package cn.com.eship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by simon on 16/7/14.
 */
@Controller
@RequestMapping("system")
public class SystemController {

    @RequestMapping("login")
    public String loginPage() {
        return "login";
    }

    @RequestMapping("loginVilidate")
    public String loginVilidate() {
        return "redirect:/index/indexPage.do";
    }

}
