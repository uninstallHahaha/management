package per.aclic.managesys.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import per.aclic.managesys.model.Achi;
import per.aclic.managesys.model.Notice;
import per.aclic.managesys.model.Proj;
import per.aclic.managesys.model.User;
import per.aclic.managesys.model.mixmodel.AchiMuser;
import per.aclic.managesys.model.mixmodel.projMuser;
import per.aclic.managesys.service.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    @Autowired
    UserService userService;

    //    模板跳转通用公式
    @RequestMapping("/{first}/{second}")
    public String getPage(@PathVariable String first, @PathVariable String second) {
        String preStr = "mm/classic/mmenu/html/";
        String[] splitSec = second.split(".html");
        String resStr = preStr + first + "/" + splitSec[0];
        return resStr;
    }

    //所有通知 页面
    @RequestMapping("/getAllNoticePage")
    public String getAllNoticePage(Model model) {
        List<Notice> allNotice = noticeService.findAll();
        model.addAttribute("notices", allNotice);
        return "/mm/classic/mmenu/html/tables/notice";
    }


    //个人所有  项目界面
    //TODO 在加入用户系统后,应当更改为获取该用户的项目和活动
    @RequestMapping("/getAllProjPage")
    public String getAllProjPage(Model model) {
        List<projMuser> allProj = projService.findAllProj();
        model.addAttribute("projs", allProj);
        model.addAttribute("pageType", 1);
        return "/mm/classic/mmenu/html/tables/proj-bootstraptable";
    }

    //管理所有  项目界面
    @RequestMapping("/getProjManagePage")
    public String getProjManagePage(Model model) {
        List<projMuser> allProjAndAc = projService.findAllMUser();
        model.addAttribute("projs", allProjAndAc);
        model.addAttribute("pageType", 3);
        return "/mm/classic/mmenu/html/tables/proj-bootstraptable";
    }

    //项目详情
    @RequestMapping("/getProjInfoPage")
    public String getProjInfoPage(Model model, String id) {
        model.addAttribute("projInfo", projService.findOneMUser(id));
        model.addAttribute("projRelativeMessage", messageService.findByProjid(id));
        return "/mm/classic/mmenu/html/tables/projDetail";
    }

    //个人所有  活动界面
    //TODO 在加入用户系统后,应当更改为获取该用户的项目和活动
    @RequestMapping("/getAllAcPage")
    public String getAllAcPage(Model model) {
        List<projMuser> allProj = projService.findAllAc();
        model.addAttribute("projs", allProj);
        model.addAttribute("pageType", 2);
        return "/mm/classic/mmenu/html/tables/proj-bootstraptable";
    }

    //学术成果页面 - 个人
    @RequestMapping("/getAchiPPage")
    public String getAchiPPage(Model model, HttpSession session) {
        User user = (User)session.getAttribute("USER");
        if(user == null){
            user = new User("2bfadee2-d089-4c8d-b60b-906c","test","testpass",1);
        }
        List<Achi> allAchis = achiService.findAllByUser(user.getId());
        model.addAttribute("achis", allAchis);
        model.addAttribute("pageType","p");
        return "/mm/classic/mmenu/html/tables/achi";
    }


    //学术成果页面 - 中心
    @RequestMapping("/getAchiCenterPage")
    public String getAchiCenterPage(Model model, HttpSession session) {
        List<AchiMuser> allAchis = achiService.findAllMUser();
        model.addAttribute("achis", allAchis);
        model.addAttribute("pageType","center");
        return "/mm/classic/mmenu/html/tables/achi";
    }


    //学术成果详情页面
    @RequestMapping("/getPage/getAchiDetailPage/{achid}")
    public String getAchiDetailPage(Model model, @PathVariable String achid) {
        //成果详情
        model.addAttribute("achiDetail", achiService.findById(achid));
        //相关评论
        model.addAttribute("relativeMessages", messageService.findByProjid(achid));
        return "/mm/classic/mmenu/html/tables/achiDetail";
    }

    //发布成果
    @RequestMapping("/getAddAchiPage")
    public String getAddAchiPage(Model model, String achiid) {
        if(achiid != null && achiid != ""){
            model.addAttribute("achiInfo",achiService.findOne(achiid));
        }
        return "/mm/classic/mmenu/html/tables/achi-form";
    }

  //项目/学术活动中心
    @RequestMapping("/getProjCenterPage")
    public String getProjCenterPage(Model model, String condition) {
        List<Proj> allProjs = new ArrayList<Proj>();
        if (null != condition) {
            model.addAttribute("condition", condition);
            allProjs = projService.findAllByCondition(condition);
        } else {
            allProjs = projService.findAll();
        }
        model.addAttribute("projs2Center", allProjs);
        model.addAttribute("count", allProjs.size());
        return "/mm/classic/mmenu/html/tables/proj-center";
    }

    //项目表单页面
    @RequestMapping("/getProjFormPage")
    public String getProjFormPage(Model model, String id) {
        if (null != id) {
            Proj proj = projService.findOne(id);
            model.addAttribute("pageType", proj.getType() / 10 == 1 ? 1 : 2);
            model.addAttribute("projInfo", proj);
        }
        return "/mm/classic/mmenu/html/tables/proj-form";
    }

    //用户管理
    @RequestMapping("/getUserManagePage")
    public String getUserManagePage(Model model, String name) {
        List<User> allUser = new ArrayList<User>();
        if (null != name) {
            allUser = userService.findAllByCondition(name);
        } else {
            allUser = userService.findAll();
        }
        List<User> allNormal = new ArrayList<User>();
        List<User> allManager = new ArrayList<User>();
        for (User user : allUser) {
            if (user.getRole() == 1) allNormal.add(user);
            if (user.getRole() == 2) allManager.add(user);
        }
        model.addAttribute("all", allUser);
        model.addAttribute("allNormal", allNormal);
        model.addAttribute("allManager", allManager);
        return "/mm/classic/mmenu/html/tables/userManage";
    }

    //日志管理
    @RequestMapping("/getLogPage")
    public String getLogPage(){
        return "/mm/classic/mmenu/html/tables/log-bootstrap";
    }
}