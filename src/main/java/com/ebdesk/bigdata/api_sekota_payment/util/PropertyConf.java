/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebdesk.bigdata.api_sekota_payment.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

/**
 *
 * 
 */
public class PropertyConf {

  private static final Logger logger = LoggerFactory.getLogger(PropertyConf.class);
  private static Environment env;

  public static void setEnv(Environment aEnv) {
    env = aEnv;
  }

  public static String getProperty(String key) {
    return env.getProperty(key);
  }

  public static Map<String, Object> getConfig(String jsonFilePath) {
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> result = null;
    try {
      result = mapper.readValue(new File(jsonFilePath), Map.class);
    } catch (IOException ex) {
//      Logger.getLogger(ShareUtil.class.getName()).log(Level.SEVERE, null, ex);
      logger.error(ex.toString());
    }
    return result;
  }

}
