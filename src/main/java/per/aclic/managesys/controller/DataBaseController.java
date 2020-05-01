package per.aclic.managesys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

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
            String command = "cmd  /c  mysqldump -t management -u root -p" + pass + " --tables " + tablstr + ">"
                    + path + "/backup" + new java.util.Date().getTime()
                    + ".sql";
            Process process = Runtime.getRuntime().exec(command);
            System.out.println("success!!!");
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
