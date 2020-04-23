package per.aclic.managesys.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import per.aclic.managesys.Utils.Utils;
import per.aclic.managesys.model.Log;
import per.aclic.managesys.model.User;
import per.aclic.managesys.service.LogService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LogInterceptor implements HandlerInterceptor {

    @Autowired
    LogService logService;

    /**
     * controller 执行之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //static
        if(request.getRequestURI().contains(".")){
            return true;
        }
        //login
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("USER");
        if(null == user){
            response.sendRedirect("/login");
            return false;
        }
        //log
        Log log = new Log(Utils.genUUID(),
                "-","-",0,request.getRequestURI());
        if(null != user){
            log.setUserid(user.getId());
            log.setUsername(user.getName());
            log.setUserrole(user.getRole());
        }
        logService.addLog(log);
        return true;
    }

    /**
     * controller 执行之后，且页面渲染之前调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        System.out.println("------postHandle-----");
    }

    /**
     * 页面渲染之后调用，一般用于资源清理操作
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        System.out.println("------afterCompletion-----");

    }
}
