package springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;



@SpringBootApplication
@EnableCaching  //开启基于注解的缓存
@EnableScheduling
@MapperScan("springboot.mybatis.mapper")
//@ServletComponentScan(basePackages = "springboot.Filters")  //json请求体拦截器
public class springsecurityoauth2jwt {
    public static void main(String[] args){
        SpringApplication.run(springsecurityoauth2jwt.class,args);
    }

}
