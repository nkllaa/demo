package com.example.demo.config;

import com.example.demo.interceptor.XYPMinInterceptor;
import com.example.demo.interceptor.XYPWXInterceptor;
import com.example.demo.utils.RedisUtil;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.*;

import javax.servlet.MultipartConfigElement;


@Configuration
public class XYPWebConfig implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        //允许跨域请求
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }
    public void addInterceptors(InterceptorRegistry registry){
        //注册拦截器
        //网页端拦截器                                                                                         //不拦截的路径
        registry.addInterceptor(new XYPMinInterceptor()).addPathPatterns("/userMain/**","/folderAndFile/**").excludePathPatterns("/static/**",
                "/userMain/Logout","/userMain/userLogin","/userMain/userReg");
        //小程序拦截器
        registry.addInterceptor(new XYPWXInterceptor()).addPathPatterns("/userXCX/**").excludePathPatterns("/userXCX/userLogin",
                "/userXCX/userLogin","/userXCX/userRegister");
    }
    public void addViewControllers( ViewControllerRegistry registry ) {
        registry.addViewController( "/" ).setViewName( "forward:/login.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        WebMvcConfigurer.super.addViewControllers( registry );
    }
    public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry){
        //配置虚拟路径
        resourceHandlerRegistry.addResourceHandler("/file/**").addResourceLocations("file:e:/XYP/");
             //加载静态资源,如css，js等
        //resourceHandlerRegistry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }

    /**
     * @Description 设置文件上传的大小
     * @Author wanxin
     * @Date 2020/2/9 15:09
     * @Param []
     * @return javax.servlet.MultipartConfigElement
     **/
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(DataSize.ofMegabytes(100)); // KB,MB
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(300));
        return factory.createMultipartConfig();
    }
}
