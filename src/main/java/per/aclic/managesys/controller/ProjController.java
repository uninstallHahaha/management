package per.aclic.managesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.aclic.managesys.model.Proj;
import per.aclic.managesys.service.ProjService;

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

    @ResponseBody
    @RequestMapping("/obtainProjs")
    public List<Proj> obtainProjs(){
        List<Proj> allProjs = projService.findAll();
        return allProjs;
    }

}


