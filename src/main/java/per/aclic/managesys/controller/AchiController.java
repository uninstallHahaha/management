package per.aclic.managesys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.model.Achi;
import per.aclic.managesys.model.Proj;
import per.aclic.managesys.model.User;
import per.aclic.managesys.service.AchiService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

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
            MultipartFile achipic,
            int subject, String detail) throws Exception {
        User user = (User) session.getAttribute("USER");
        //test
        if (user == null) {
            user = new User();
            user.setId("2bfadee2-d089-4c8d-b60b-906c");
            user.setName("test");
            user.setPass("testpass");
            user.setRole(1);
        }

        //file upload
        //获取上传的位置(判断有无, 无责新建)
        String path = ResourceUtils.getURL("classpath:").getPath().concat("/static/uploads/achipics");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        //获取上传文件的名称
        String filename = achipic.getOriginalFilename();
        //使用uuid使得文件名唯一
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 6);
        filename = uuid + "_" + filename;
        //上传
        try {
            achipic.transferTo(new File(path, filename));
        } catch (Exception e) {
            //上传失败
        }

        int res = 0;
        if (null != id && !id.equals("")) {
            Achi oneAchi = achiService.findOne(id);
            res = achiService.modProj(new Achi(
                    id,
                    name,
                    user.getId(),
                    subject,
                    detail,
                    filename,
                    ctime
            ));
            if(res == 1){
                return "redirect:/getpage/getAddAchiResPage/21";
            }else{
                return "redirect:/getpage/getAddAchiResPage/20";
            }
        } else {
            Achi achi = new Achi();
            achi.setDetail(detail);
            achi.setUserid(user.getId());
            achi.setType(subject);
            achi.setId(Utils.genUUID());
            achi.setName(name);
            achi.setCtime(ctime);
            achi.setPic(filename);
            res = achiService.addAchi(achi);
            if(res == 1){
                return "redirect:/getpage/getAddAchiResPage/11";
            }else{
                return "redirect:/getpage/getAddAchiResPage/10";
            }
        }
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
