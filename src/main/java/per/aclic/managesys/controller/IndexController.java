package per.aclic.managesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.aclic.managesys.model.User;
import per.aclic.managesys.model.UserTest;
import per.aclic.managesys.service.AchiService;
import per.aclic.managesys.service.NoticeService;
import per.aclic.managesys.service.ProjService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    NoticeService noticeService;
    @Autowired
    ProjService projService;
    @Autowired
    AchiService achiService;


    //    test
    @ResponseBody
    @RequestMapping("getUser")
    public UserTest getUser() {
        return new UserTest("alice", "1234");
    }


    @RequestMapping("/login")
    public String getLogin() {
        return "/mm/classic/mmenu/html/login/login";
    }


    @RequestMapping("/getSignUpPage")
    public String getSignUpPage() {
        return "/mm/classic/mmenu/html/login/register";
    }


    @RequestMapping("/index")
    public String getIndex(Model model , HttpSession session) {
        model.addAttribute("str", "个人主页");
        //仪表盘
        User user = (User)session.getAttribute("USER");
        model.addAttribute("userProjCount",
                projService.findAllProjByUser(user.getId()).size());
        model.addAttribute("userProjPercent",
                projService.findAllProjByUser(user.getId()).size()/projService.findAllProj().size());
        model.addAttribute("userAcCount",
                projService.findAllAcByUser(user.getId()).size());
        model.addAttribute("userAcPercent",
                projService.findAllAcByUser(user.getId()).size()/projService.findAllAc().size());
        model.addAttribute("userAchiCount",
                achiService.findAllByUser(user.getId()).size());
        model.addAttribute("userAchiPercent",
                achiService.findAllByUser(user.getId()).size()/achiService.findAll().size());
        //通知
        String[] iconList = {" wb-chat", " wb-image", " wb-file", " wb-map"};
        model.addAttribute("recentNotices", noticeService.findRecentNotice(7));
        model.addAttribute("noticeIcons", iconList);
        String[] iconColorList = {" bg-green-600", " bg-blue-600", " bg-cyan-600", " bg-orange-600"};
        model.addAttribute("iconColorList", iconColorList);
        //项目和学术活动
        model.addAttribute("recentProjs", projService.findByLimit(7));
        return "mm/classic/mmenu/html/dashboard/general-team";
    }


}
