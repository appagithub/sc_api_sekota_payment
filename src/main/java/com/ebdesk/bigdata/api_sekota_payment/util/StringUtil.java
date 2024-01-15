/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebdesk.bigdata.api_sekota_payment.util;

//import com.ebdesk.bigdata.api_sekota_payment.service.MidtransCoreApi;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 
 */
public class StringUtil {
  private final Logger logger = LoggerFactory.getLogger(StringUtil.class);
  
  public Map convertToMap(String data){
    ObjectMapper oMapper = new ObjectMapper();
    
    return oMapper.convertValue(data, Map.class);
  }
  
  public String getError(Exception e) {
    StringWriter errors = new StringWriter();
    String result = "";
    try {
      e.printStackTrace(new PrintWriter(errors));
      result = errors.toString();
    } catch (Exception ex) {
      logger.error(getError(ex));
    } finally {
      try {
        errors.close();
      } catch (IOException ex) {
        logger.error(getError(ex));
      }
    }

    return result;
  }
  
  public Date stringToDate(String value, String format) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat(format);

    return sdf.parse(value);
  }
}
