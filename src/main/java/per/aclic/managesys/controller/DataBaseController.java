package per.aclic.managesys.controller;

import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class DataBaseController {

    @RequestMapping(value = "/databasebackup", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String databasebackup(HttpServletResponse response,
                                 String pass,
                                 @RequestParam("ts[]") List<String> ts) throws Exception {
        //获取上传的位置(判断有无, 无责新建)
        String path = ResourceUtils.getURL("classpath:").getPath().concat("static/databasebackup");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        path = path.substring(1, path.length());
        try {
            String tablstr = "";
            for (int i = 0; i < ts.size(); i++) {
                tablstr += ts.get(i) + " ";
            }
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
            String command = "cmd  /c  mysqldump -t management -u root -p" + pass + " --tables " + tablstr + ">"
                    + path + "/backup_" + simpleDateFormat.format(new Date())
                    + ".sql";
            Process process = Runtime.getRuntime().exec(command);
//            System.out.println("success!!!");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
