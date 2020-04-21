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
import java.util.Date;
import java.util.List;

@Controller
public class ProjController {
    @Autowired
    ProjService projService;

    @ResponseBody
    @RequestMapping("/addProj")
    public int addProj(Proj proj){
        return projService.addProj(proj);
    }

//    add proj from form
    @RequestMapping("/addProjForm")
    public String addProjForm(
            String name, Date ctime, String github,
            int subject, int type,String detail
            ){
        Proj pj = new Proj();
        pj.setDetail(detail);
        pj.setUserid("djflajfklj");
        pj.setType(type*10+subject);
        pj.setState(1);
        pj.setCount(1);
        pj.setId(Utils.genUUID());
        pj.setName(name);
        pj.setCtime(ctime);
        int res = projService.addProj(pj);
        if(res == 1){
            return "/mm/classic/mmenu/html/tables/proj-bootstraptable";
        }
        return "/mm/classic/mmenu/html/tables/proj-form";
    }

    @ResponseBody
    @RequestMapping("/obtainProjs")
    public List<Proj> obtainProjs(){
        List<Proj> allProjs = projService.findAllProj();
        return allProjs;
    }


    @ResponseBody
    @RequestMapping("/delProj")
    public int delProj(String id){
        return projService.delProj(id);
    }

    @ResponseBody
    @RequestMapping("/delProjList")
    public int delProjList(@RequestParam(value = "ids[]") List<String> ids){
        return projService.delProjList(ids);
    }



}


