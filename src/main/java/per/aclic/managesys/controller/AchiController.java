package per.aclic.managesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.aclic.managesys.model.Achi;
import per.aclic.managesys.service.AchiService;

@Controller
public class AchiController {

    @Autowired
    AchiService achiService;

    @ResponseBody
    @RequestMapping("/addAchi")
    public int addAchi(Achi achi){
        return achiService.addAchi(achi);
    }
}
