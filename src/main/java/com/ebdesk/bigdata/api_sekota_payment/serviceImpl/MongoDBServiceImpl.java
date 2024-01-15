package com.ebdesk.bigdata.api_sekota_payment.serviceImpl;

import com.ebdesk.bigdata.api_sekota_payment.service.MongoDBService;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * 
 */
@Service
public class MongoDBServiceImpl implements MongoDBService {
  
  @Autowired
  MongoTemplate mongoTemplate;
  private final Logger logger = LoggerFactory.getLogger(MongoDBServiceImpl.class);
  
  @Override
  public void insertMongodb(Map data) {
    logger.info("insert to DB : " + data);
    mongoTemplate.insert(data, "payment_log");
  }
  
}
