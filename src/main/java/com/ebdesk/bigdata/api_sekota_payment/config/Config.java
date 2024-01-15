/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebdesk.bigdata.api_sekota_payment.config;

import com.ebdesk.bigdata.api_sekota_payment.util.PropertyConf;
import com.midtrans.ConfigFactory;
import com.midtrans.service.MidtransCoreApi;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@PropertySource("file:${global.conf}/global.properties")
public class Config {

  @Autowired
  private Environment env;

  @Autowired
  private void dataSource() {
    PropertyConf.setEnv(env);
  }

  @Bean
  public MongoClient mongoClient() {
    return new MongoClient(new MongoClientURI(PropertyConf.getProperty("mongo.url")));
  }

  @Bean
  public MongoTemplate mongoTemplate() {
    MongoTemplate mongoTemplate = new MongoTemplate(mongoClient(), PropertyConf.getProperty("mongo.db"));

    return mongoTemplate;
  }

  @Bean
  public ProducerFactory<String, String> producerFactory() {
    Map<String, Object> configProps = new HashMap<>();
    configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, PropertyConf.getProperty("bootstrap.kafka"));
    configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
    configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

    return new DefaultKafkaProducerFactory<>(configProps);
  }

  @Bean
  public KafkaTemplate<String, String> kafkaTemplate() {
    return new KafkaTemplate<>(producerFactory());
  }

  @Bean
  public MidtransCoreApi coreApi() {
    System.out.println("server : " + PropertyConf.getProperty("sb-mid-server"));
    System.out.println("client : " + PropertyConf.getProperty("sb-mid-client"));
    return new ConfigFactory(new com.midtrans.Config(PropertyConf.getProperty("sb-mid-server"), PropertyConf.getProperty("sb-mid-client"), false)).getCoreApi();
  }

  @Bean
  public FilterRegistrationBean corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedOrigin("*");
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    source.registerCorsConfiguration("/**", config);
    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
    bean.setOrder(0);
    return bean;
  }
}
