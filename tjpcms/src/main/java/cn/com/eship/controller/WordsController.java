package cn.com.eship.controller;


import cn.com.eship.utils.ConfigUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by simon on 16/9/13.
 */
@Controller
@RequestMapping("/words")
public class WordsController {

    @RequestMapping("toWordsPage")
    public String wordsPage() {
        return "wordsList";
    }

    @RequestMapping(value = "/uploadWordsFile", method = RequestMethod.POST)
    public String uploadWordsFile(@RequestParam MultipartFile upfile, Model model) {
        try {
            if (upfile == null || upfile.getBytes() == null || upfile.getBytes().length <= 0) {
                model.addAttribute("upfileStatus", "分词文件不能为空");
                return "wordsList";
            }
            if (!"text/plain".equals(upfile.getContentType())) {
                model.addAttribute("upfileStatus", "请上传文本文件");
                return "wordsList";
            }

            File file = new File(ConfigUtils.readValue("upfile.properties", "filePath"));
            FileUtils.writeByteArrayToFile(file, upfile.getBytes());
            model.addAttribute("upfileStatus", "分词文件上传成功");
        } catch (Exception e) {
            model.addAttribute("upfileStatus", "分词文件上传失败");
        }

        return "wordsList";
    }


    @RequestMapping("/downLoadWordsFile")
    public void downLoadWordsFile(HttpServletResponse response) throws Exception {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/plain");
        IOUtils.copy(new FileInputStream(ConfigUtils.readValue("upfile.properties", "filePath")), response.getOutputStream());
    }

}
