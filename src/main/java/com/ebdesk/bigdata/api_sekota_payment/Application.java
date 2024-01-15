package com.ebdesk.bigdata.api_sekota_payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
public class Application extends SpringBootServletInitializer {

  public static void main(String[] args) {
    if (args.length == 1) {
      System.getProperties().put("server.port", args[0]);
    } else {
      System.getProperties().put("server.port", 8820);
    }
    if (System.getProperty("logging.config") == null) {
      System.getProperties().put("logging.config", "./conf/logback.xml");
    }
    SpringApplication.run(Application.class, args);
  }

//  @Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurerAdapter() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/**").allowedOrigins("http://192.168.150.42:8084");
//			}
//		};
//	}
}
