package per.aclic.managesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.model.Notice;
import per.aclic.managesys.service.NoticeService;

import java.util.Date;

@Controller
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @ResponseBody
    @RequestMapping("/addNotice")
    public int addNotice(String title,String content){
//        for(int i = 10; i< 20;i++){
//            Notice nt = new Notice(Utils.genUUID(),"通知"+i,
//                    "contentcontentcontentcontentcontentcontentcontentcont" +
//                            "entcontentcontentcontentcontentcontent",Utils.genUUID());
//            noticeService.addNotice(nt);
//        }
        Notice notice = new Notice(Utils.genUUID(),title,content,Utils.genUUID());
        notice.setCtime(new Date());
        int res = noticeService.addNotice(notice);
        return res;
    }

    @ResponseBody
    @RequestMapping("/notice/delNotice/{cuserid}")
    public int delNotice(@PathVariable String cuserid){
        return noticeService.delNotice(cuserid);
    }
}
