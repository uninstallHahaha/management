package per.aclic.managesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.model.Proj;
import per.aclic.managesys.model.User;
import per.aclic.managesys.model.mixmodel.projMuser;
import per.aclic.managesys.service.ProjService;

import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class ProjController {
    @Autowired
    ProjService projService;

    @ResponseBody
    @RequestMapping("/addProj")
    public int addProj(Proj proj) {
        return projService.addProj(proj);
    }

    //    add proj from form
    //    or mod proj from form
    @RequestMapping("/addProjForm")
    public String addProjForm(
            HttpSession session,
            HttpServletRequest request,
            HttpServletResponse response,
            String id,
            String name, Date ctime, String github,
            int subject, int type, String detail) throws ServletException, IOException {
        User user = (User) session.getAttribute("USER");
        if(user == null){
            user = new User();
            user.setId(Utils.genUUID());
            user.setName("test");
            user.setPass("testpass");
            user.setRole(1);
        }
        int res=0;
        if (null != id && id != "") {
            Proj oneProj = projService.findOne(id);
            res = projService.modProj(new Proj(
                    id, user.getId(),
                    name, 1,
                    oneProj.getCount(), detail,
                    type * 10 + subject, ctime
            ));
        } else {
            Proj pj = new Proj();
            pj.setDetail(detail);
            pj.setUserid(user.getId());
            pj.setType(type * 10 + subject);
            pj.setState(1);
            pj.setCount(1);
            pj.setId(Utils.genUUID());
            pj.setName(name);
            pj.setCtime(ctime);
            res = projService.addProj(pj);
        }
        if(type == 1){
            return "forward:/getAllProjPage";
        }else{
            return "forward:/getAllAcPage";
        }
    }

    @ResponseBody
    @RequestMapping("/obtainProjs")
    public List<projMuser> obtainProjs() {
        List<projMuser> allProjs = projService.findAllProj();
        return allProjs;
    }


    @ResponseBody
    @RequestMapping("/obtainAcs")
    public List<projMuser> obtainAcs() {
        List<projMuser> allProjs = projService.findAllAc();
        return allProjs;
    }




    @ResponseBody
    @RequestMapping("/delProj")
    public int delProj(String id) {
        return projService.delProj(id);
    }

    @ResponseBody
    @RequestMapping("/delProjList")
    public int delProjList(@RequestParam(value = "ids[]") List<String> ids) {
        return projService.delProjList(ids);
    }



    @ResponseBody
    @RequestMapping("/modProjCount")
    public int modProjCount(String projid) {
        return projService.modProjCount(projid);
    }



    @ResponseBody
    @RequestMapping("/stopProj")
    public int stopProj(String projid) {
        return projService.stopProj(projid);
    }
}


