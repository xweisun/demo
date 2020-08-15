package com.sun.config;

import com.sun.handler.AuthorityInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.Arrays;

/**
 * Created by sunxw on 2018-08-23 15:14
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")   //application
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");

    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedOrigins("*")
                .allowedMethods("*");

    }
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorityInterceptor())//.addPathPatterns(Arrays.asList("/test/**","/**"))
                .excludePathPatterns(Arrays.asList(//"classpath:/META-INF/resources/webjars/",
                         "/**"

                        //"/null/swagger**/**",
                       // "classpath:/META-INF/**", "classpath:/static/", "/swagger**/**","/favicon.ico", "/webjars/**","/error"
                //"/swagger-ui.html"
                        ));
        /*registry.addInterceptor(new AuthorityInterceptor())
                .addPathPatterns(Arrays.asList("/**"))//拦截路径
                .excludePathPatterns(Arrays.asList("/swagger**","/webjar**"));//不拦截路径
                super.addInterceptors(registry);*/
    }

    @Bean
    public AuthorityInterceptor authorityInterceptor() {
        return new AuthorityInterceptor();
    }


}
