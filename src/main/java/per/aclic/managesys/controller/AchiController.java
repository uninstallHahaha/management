package per.aclic.managesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.model.Achi;
import per.aclic.managesys.service.AchiService;

@Controller
public class AchiController {

    @Autowired
    AchiService achiService;

    @ResponseBody
    @RequestMapping("/addAchi")
    public int addAchi(Achi achi) {
        return achiService.addAchi(achi);
    }

    @ResponseBody
    @RequestMapping("/addAchiUtil")
    public int addAchiUtil() {
        for (int i = 0; i < 10; i++) {
            Achi achi = new Achi(Utils.genUUID(), "testAchi" + i,
                    "2bfadee2-d089-4c8d-b60b-906c", (int)Math.random()*3,
                    "etsetsetsetsetsetsetsets");
            achiService.addAchi(achi);
        }
        return 1;
    }
}
