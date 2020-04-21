package per.aclic.managesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.model.Proj;
import per.aclic.managesys.service.ProjService;

import javax.rmi.CORBA.Util;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            HttpServletRequest request,
            HttpServletResponse response,
            String id,
            String name, Date ctime, String github,
            int subject, int type, String detail) throws ServletException, IOException {
        if (null != id) {
            int modRes = projService.modProj(new Proj(
                    id, "djflajfklj",
                    name, 1,
                    1, detail,
                    type * 10 + subject, ctime
            ));
        } else {
            Proj pj = new Proj();
            pj.setDetail(detail);
            pj.setUserid("djflajfklj");
            pj.setType(type * 10 + subject);
            pj.setState(1);
            pj.setCount(1);
            pj.setId(Utils.genUUID());
            pj.setName(name);
            pj.setCtime(ctime);
            int res = projService.addProj(pj);
        }
        return "forward:/getAllProjPage";

    }

    @ResponseBody
    @RequestMapping("/obtainProjs")
    public List<Proj> obtainProjs() {
        List<Proj> allProjs = projService.findAllProj();
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


}


