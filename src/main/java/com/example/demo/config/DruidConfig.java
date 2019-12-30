package com.example.demo.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;

/**
 * <p>ClassName: 配置druid</p>
 * <p>@Description: TODO</p>
 * <p>@author WanXin</p>
 * <p>@date 2019年8月3日下午8:38:42</p>
 */
@Configuration
public class DruidConfig {
	/**
	 * 读取配置文件里的属性值
	 */
	@Bean
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource druid() {
		DruidDataSource dataSource=new DruidDataSource();
		return	dataSource;
	}
	//配置druid监控界面
	@Bean
	public ServletRegistrationBean statViewServlet() {
		ServletRegistrationBean bean= new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
		Map<String, String> map=new HashMap<String, String>();
		map.put("loginUsername", "admin");
		map.put("loginPassword", "123456");
		map.put("allow", "");//允许谁访问，添加IP白名单
		map.put("deny", "");//不允许谁访问，添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
		bean.setInitParameters(map);
		return bean;
	}
	//配置web的filter
	@Bean
	public FilterRegistrationBean  webStatFilter() {
		FilterRegistrationBean bean=new FilterRegistrationBean();
		bean.setFilter(new WebStatFilter());
		Map<String, String> map=new HashMap<String, String>();
		map.put("exclusions", "*.js,*.css,/druid/*");//不拦截的请求
		bean.setInitParameters(map);
		
		bean.setUrlPatterns(Arrays.asList("/*"));
		return bean;
	}
}
