package per.aclic.managesys.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import per.aclic.managesys.interceptor.LogInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{
    @Autowired
    LogInterceptor interceptor;

    //重写添加拦截器的方法
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //需要拦截的路径
        String[] addPathPattents = {
                "/**"
        };
        //不要拦截的路径
        String[] excludePathPattents = {
                "/login","/signin","/getSignUpPage","signup"
        };
        //注册拦截器
        registry.addInterceptor(interceptor)
                .addPathPatterns(addPathPattents).excludePathPatterns(excludePathPattents);
    }
}
