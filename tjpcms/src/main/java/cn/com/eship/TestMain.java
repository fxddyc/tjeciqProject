package cn.com.eship;


import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;

import java.io.*;

public class TestMain {
    public static void main(String[] args) throws Exception {
        testWrite();
    }

    private static void testWrite() throws Exception {
        String templatePath = "F:/test1.doc";
        InputStream is = new FileInputStream(templatePath);
        HWPFDocument doc = new HWPFDocument(is);
        Range range = doc.getRange();
        //把range范围内的${reportDate}替换为当前的日期
        range.replaceText("${title}", "疫情周报");
        range.replaceText("${appleAmt}", "100.00");
        range.replaceText("${bananaAmt}", "200.00");
        range.replaceText("${totalAmt}", "300.00");
        OutputStream os = new FileOutputStream("F:\\write.doc");
        //把doc输出到输出流中
        doc.write(os);
        is.close();
        os.close();
    }

    /**
     * 关闭输入流
     * @param is
     */
    private void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭输出流
     * @param os
     */
    private void closeStream(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


