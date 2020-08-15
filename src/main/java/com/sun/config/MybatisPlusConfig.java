package com.sun.config;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties
@MapperScan("com.sun.**.mapper")
public class MybatisPlusConfig {

    @Autowired
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    @Autowired(required = false)
    private Interceptor[] interceptors;
    @Autowired
    private MybatisPlusProperties properties;





    /**
     * mybatis-plus分页插件
     *
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql");
        return page;
    }

    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();

        //performanceInterceptor.setMaxTime(1000);

        //SQL是否格式化 默认false-->

        performanceInterceptor.setFormat(true);
        performanceInterceptor.isWriteInLog();
        return performanceInterceptor;
    }

   /* @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public HikariConfig hikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        //hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        return hikariConfig;
    }*/

 /*  @Bean
   @ConfigurationProperties(prefix = "spring.datasource")
   public DataSource druidDataSource(){
       DruidDataSource dataSource = new DruidDataSource();
       return  dataSource;
   }
*/




    @Bean
    @ConfigurationProperties(prefix ="write.datasource")
    public DataSource writeDataSource(){
        return new DruidDataSource();
    }

    @Bean
    @ConfigurationProperties(prefix ="read.datasource")
    public DataSource readDataSource(){
        return new DruidDataSource();
    }


    @Bean
    public DataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> map = new HashMap<>(2);
        map.put("write",writeDataSource());
        map.put("read",readDataSource());
        dynamicDataSource.setDefaultTargetDataSource(writeDataSource());
        dynamicDataSource.setTargetDataSources(map);
        return dynamicDataSource;
    }

   /* @Bean
    public PlatformTransactionManager txManager(DynamicDataSource dynamicDataSource){
        return new DataSourceTransactionManager(dynamicDataSource);
    }*/

    //配置Druid的监控
    //1、配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> initParams = new HashMap<>();

        initParams.put("loginUsername","admin");
        initParams.put("loginPassword","123456");
        initParams.put("allow","");//默认就是允许所有访问
        //initParams.put("deny","192.168.15.21");

        bean.setInitParameters(initParams);
        return bean;
    }

    //2、配置一个web监控的filter
   @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String,String> initParams = new HashMap<>();
        initParams.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParams);

        bean.setUrlPatterns(Arrays.asList("/*"));

        return  bean;
    }

   @Autowired
//   DataSource druidDataSource;
    DataSource dynamicDataSource;
    /**
     * 这里全部使用mybatis-autoconfigure 已经自动加载的资源,不手动指定
     *
     * 配置文件和mybatis-boot的配置文件同步
     */
    @Bean
    public MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean() {
       /*HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);*/

        MybatisSqlSessionFactoryBean mybatisPlus = new MybatisSqlSessionFactoryBean();
        mybatisPlus.setDataSource(dynamicDataSource);

        mybatisPlus.setConfiguration(properties.getConfiguration());

        if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
            mybatisPlus.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
        }
        if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
            mybatisPlus.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
        }
        if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
            mybatisPlus.setMapperLocations(this.properties.resolveMapperLocations());
        }

        // MP 全局配置，更多内容进入类看注释

        GlobalConfiguration globalConfig = new GlobalConfiguration();
        globalConfig.setDbType(DBType.MYSQL.name());//数据库类型

        // ID 策略 AUTO->`0`("数据库ID自增") INPUT->`1`(用户输入ID") ID_WORKER->`2`("全局唯一ID") UUID->`3`("全局唯一ID")
        globalConfig.setIdType(2);

        //MP 属性下划线 转 驼峰 , 如果原生配置 mc.setMapUnderscoreToCamelCase(true) 开启，该配置可以无。
        globalConfig.setDbColumnUnderline(true);

        if (!ObjectUtils.isEmpty(this.interceptors)) {
            mybatisPlus.setPlugins(this.interceptors);
        }

        mybatisPlus.setGlobalConfig(globalConfig);
        MybatisConfiguration mc = new MybatisConfiguration();

        // 对于完全自定义的mapper需要加此项配置，才能实现下划线转驼峰
        mc.setMapUnderscoreToCamelCase(true);

        mc.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        mybatisPlus.setConfiguration(mc);
        return mybatisPlus;
    }

    //配置fastjson进行消息转换
    @Bean
    public HttpMessageConverters fastjsonHttpMessageConverters(){
        //创建FastJson消息转换器
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        //创建配置对象
        FastJsonConfig config = new FastJsonConfig();
        //配置序列化进行格式化json数据
        config.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //将此配置传给转换器
        converter.setFastJsonConfig(config);

        HttpMessageConverter con = converter;
        //通过构造方法传递对象
        return new HttpMessageConverters(con);

    }
}
