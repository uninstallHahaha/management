package per.aclic.managesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.model.Achi;
import per.aclic.managesys.model.Proj;
import per.aclic.managesys.model.User;
import per.aclic.managesys.service.AchiService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class AchiController {

    @Autowired
    AchiService achiService;

    @ResponseBody
    @RequestMapping("/addAchi")
    public int addAchi(Achi achi) {
        return achiService.addAchi(achi);
    }


    //    add achi from form
    //    or mod achi from form
    @RequestMapping("/addAchiForm")
    public String addAchiForm(
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response,
            String id,
            String name, Date ctime,
            int subject, String detail) throws ServletException, IOException {
        User user = (User) session.getAttribute("USER");
        if (user == null) {
            user = new User();
            user.setId("2bfadee2-d089-4c8d-b60b-906c");
            user.setName("test");
            user.setPass("testpass");
            user.setRole(1);
        }
        int res = 0;
        if (null != id && id != "") {
            Achi oneAchi = achiService.findOne(id);
            res = achiService.modProj(new Achi(
                    id,
                    name,
                    user.getId(),
                    subject,
                    detail,
                    ctime
            ));
        } else {
            Achi achi = new Achi();
            achi.setDetail(detail);
            achi.setUserid(user.getId());
            achi.setType(subject);
            achi.setId(Utils.genUUID());
            achi.setName(name);
            achi.setCtime(ctime);
            res = achiService.addAchi(achi);
        }
        return "forward:/getAchiPPage";
    }

    @ResponseBody
    @RequestMapping("/addAchiUtil")
    public int addAchiUtil() {
        for (int i = 0; i < 10; i++) {
            Achi achi = new Achi(Utils.genUUID(), "testAchi" + i,
                    "2bfadee2-d089-4c8d-b60b-906c", (int) Math.random() * 3,
                    "etsetsetsetsetsetsetsets");
            achiService.addAchi(achi);
        }
        return 1;
    }
}
