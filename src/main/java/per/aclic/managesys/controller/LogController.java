package per.aclic.managesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.model.Log;
import per.aclic.managesys.service.LogService;

import java.util.List;

@Controller
public class LogController {

    @Autowired
    LogService logService;

    @ResponseBody
    @RequestMapping("/addLog")
    public int addLog(){
        for(int i=0;i<20;i++){
            Log log = new Log(Utils.genUUID(),Utils.genUUID(),
                    "alice",(int)(Math.random()+1),
                    "addLog");
            logService.addLog(log);
        }
        return 1;
    }

    @ResponseBody
    @RequestMapping("/obtainLogs")
    public List<Log> obtainLogs(){
        return logService.findAll();
    }


    @ResponseBody
    @RequestMapping("/delLog")
    public int delLog(String id){
        return logService.delLog(id);
    }

    @ResponseBody
    @RequestMapping("/delLogList")
    public int delLogList(@RequestParam(value = "ids[]") List<String> ids) {
        return logService.delLogList(ids);
    }
}
