package per.aclic.managesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.aclic.managesys.model.UserTest;
import per.aclic.managesys.service.NoticeService;
import per.aclic.managesys.service.ProjService;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    NoticeService noticeService;
    @Autowired
    ProjService projService;


    //    test
    @ResponseBody
    @RequestMapping("getUser")
    public UserTest getUser() {
        return new UserTest("alice", "1234");
    }


    @RequestMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("str", "个人主页");
        //通知
        String[] iconList = {" wb-chat", " wb-image", " wb-file", " wb-map"};
        model.addAttribute("recentNotices", noticeService.findRecentNotice(5));
        model.addAttribute("noticeIcons", iconList);
        String[] iconColorList = {" bg-green-600", " bg-blue-600", " bg-cyan-600", " bg-orange-600"};
        model.addAttribute("iconColorList", iconColorList);
        //项目和学术活动
        model.addAttribute("recentProjs", projService.findByLimit(7));
        return "mm/classic/mmenu/html/dashboard/general-team";
    }


}
