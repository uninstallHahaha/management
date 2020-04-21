package per.aclic.managesys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import per.aclic.managesys.model.Achi;
import per.aclic.managesys.model.Notice;
import per.aclic.managesys.model.Proj;
import per.aclic.managesys.service.AchiService;
import per.aclic.managesys.service.MessageService;
import per.aclic.managesys.service.NoticeService;
import per.aclic.managesys.service.ProjService;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    NoticeService noticeService;
    @Autowired
    ProjService projService;
    @Autowired
    AchiService achiService;
    @Autowired
    MessageService messageService;

//    模板跳转通用公式
    @RequestMapping("/{first}/{second}")
    public String getPage(@PathVariable String first, @PathVariable String second) {
        String preStr = "mm/classic/mmenu/html/";
        String[] splitSec = second.split(".html");
        String resStr = preStr + first + "/" + splitSec[0];
        return resStr;
    }

//    跳转到 所有通知 页面
    @RequestMapping("/getAllNoticePage")
    public String getAllNoticePage(Model model) {
        List<Notice> allNotice = noticeService.findAll();
        model.addAttribute("notices",allNotice);
        return "/mm/classic/mmenu/html/tables/notice";
    }

    //个人所有项目界面
    //TODO 在加入用户系统后,应当更改为获取该用户的项目和活动
    @RequestMapping("/getAllProjPage")
    public String getAllProjPage(Model model) {
        List<Proj> allProj = projService.findAll();
        model.addAttribute("projs",allProj);
        return "/mm/classic/mmenu/html/tables/proj-bootstraptable";
    }

    //学术成果页面
    @RequestMapping("/getAchiPPage")
    public String getAchiPPage(Model model) {
        List<Achi> allAchis = achiService.findAll();
        model.addAttribute("achis",allAchis);
        return "/mm/classic/mmenu/html/tables/achi";
    }

    //学术成果详情页面
    @RequestMapping("/getPage/getAchiDetailPage/{achid}")
    public String getAchiDetailPage(Model model,@PathVariable String achid) {
        //成果详情
        model.addAttribute("achiDetail",achiService.findById(achid));
        //相关评论
        model.addAttribute("relativeMessages",messageService.findByProjid(achid));
        return "/mm/classic/mmenu/html/tables/achiDetail";
    }

    //项目中心界面
    @RequestMapping("/getProjCenterPage")
    public String getProjCenterPage(Model model){
        model.addAttribute("projs2Center",projService.findAllProj());
        return "/mm/classic/mmenu/html/tables/proj-center";
    }

    //项目表单页面
    @RequestMapping("/getProjFormPage")
    public String getProjFormPage(Model model){
        return "/mm/classic/mmenu/html/tables/proj-form";
    }
}