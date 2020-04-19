package per.aclic.managesys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "per.aclic.managesys.dao")
public class ManagesysApplication {

    public static void main(String[] args) {
        //测试
        SpringApplication springApplication =
                new SpringApplication(ManagesysApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        SpringApplication.run(ManagesysApplication.class, args);
    }

}
