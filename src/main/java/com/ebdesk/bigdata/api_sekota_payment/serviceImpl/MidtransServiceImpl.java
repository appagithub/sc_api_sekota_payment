package com.ebdesk.bigdata.api_sekota_payment.serviceImpl;

import com.ebdesk.bigdata.api_sekota_payment.service.MidtransService;
import com.ebdesk.bigdata.api_sekota_payment.service.MongoDBService;
import com.ebdesk.bigdata.api_sekota_payment.util.StringUtil;
//import com.midtrans.httpclient.error.MidtransError;
import com.midtrans.service.MidtransCoreApi;
import java.util.Map;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * 
 */
@Service
public class MidtransServiceImpl implements MidtransService {

  @Autowired
  private MidtransCoreApi coreApi;

  @Autowired
  private MongoDBService mongoDBService;

  private final Logger logger = LoggerFactory.getLogger(MidtransServiceImpl.class);
  @Autowired
  private GlobalServiceImpl globalServiceImpl;
  private final StringUtil stringUtil = new StringUtil();

  @Override
  public JSONObject charge(Map<String, Object> body) {
    JSONObject result = new JSONObject();
    try {
      result = coreApi.chargeTransaction(body);
      Map mResult = globalServiceImpl.getExpiredTime(result);
      result = new JSONObject(mResult);
      mongoDBService.insertMongodb(result.toMap());
    } catch (Exception e) {
      logger.error(stringUtil.getError(e));
    }

    return result;
  }

  @Override
  public JSONObject getStatus(String orderId) {
    JSONObject result = new JSONObject();
    try {
      result = coreApi.checkTransaction(orderId);

      System.out.println("===== result : " + result);
//      mongoDBService.insertMongodb(result.toMap());
    } catch (Exception e) {
      logger.error(stringUtil.getError(e));
    }

    return result;
  }

  @Override
  public JSONObject getCardToken(Map<String, String> body) {
    JSONObject result = new JSONObject();
    try {
      result = coreApi.cardToken(body);

      System.out.println("===== result : " + result);
//      mongoDBService.insertMongodb(result.toMap());
    } catch (Exception e) {
      logger.error(stringUtil.getError(e));
    }

    return result;
  }

}
