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
import java.text.NumberFormat;
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


    @RequestMapping("/getsignupforadmin")
    public String getsignupforadmin() {
        return "/mm/classic/mmenu/html/login/registerforadmin";
    }


    @RequestMapping("/index")
    public String getIndex(Model model, HttpSession session) {
        model.addAttribute("str", "个人主页");
        //仪表盘
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        User user = (User) session.getAttribute("USER");
        model.addAttribute("userProjCount",
                projService.findAllProjByUser(user.getId()).size());
        model.addAttribute("userProjPercent",
                Double.parseDouble(numberFormat.format((float) projService.findAllProjByUser(user.getId()).size()
                        / (float) projService.findAllProj().size())));
        model.addAttribute("userAcCount",
                projService.findAllAcByUser(user.getId()).size());
        model.addAttribute("userAcPercent",
                Double.parseDouble(numberFormat.format((float) projService.findAllAcByUser(user.getId()).size()
                        / (float) projService.findAllAc().size())));
        model.addAttribute("userAchiCount",
                achiService.findAllByUser(user.getId()).size());
        model.addAttribute("userAchiPercent",
                Double.parseDouble(numberFormat.format((float) achiService.findAllByUser(user.getId()).size()
                        / (float) achiService.findAll().size())));
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
