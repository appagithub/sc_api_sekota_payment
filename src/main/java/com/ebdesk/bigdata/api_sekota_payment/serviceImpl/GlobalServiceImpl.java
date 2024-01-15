/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebdesk.bigdata.api_sekota_payment.serviceImpl;

import com.ebdesk.bigdata.api_sekota_payment.util.PropertyConf;
import com.ebdesk.bigdata.api_sekota_payment.util.StringUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * 
 */
@Repository
@Transactional
@SpringBootApplication
public class GlobalServiceImpl {
  String client_key = PropertyConf.getProperty("client.key");
  StringUtil stringUtil = new StringUtil();

  public Map setToken(Map data, String type) {
    Map token = new HashMap();
    if (type.equals("klikbca")) {
      Map mPaymentData = (Map) data.remove("payment_data");
      token = getToken(
        "klikbca",
        mPaymentData.get("card_number").toString(),
        mPaymentData.get("card_exp_month").toString(),
        mPaymentData.get("card_exp_year").toString(),
        mPaymentData.get("card_cvv").toString()
      );

      token.remove("status_message");
      token.remove("status_code");
      token.remove("hash");

      if (mPaymentData.get("card_3d_secure").toString().equals("true")) {
        token.put("authentication", "true");
      } else {
        token.put("authentication", "false");
      }
    } else {
      Map mPaymentData = (Map) data.remove("payment_data");
      Map getToken = getToken("mandiri_clickpay", mPaymentData.get("card_number").toString(),
        null, null, null);
      token.put("token_id", getToken.get("token_id"));
      token.put("input3", "54321");

    }

    return token;
  }

  public Map getToken(String type, String card_number, String card_exp_month, String card_exp_year,
    String card_cvv) {
    OkHttpClient client = new OkHttpClient();
    Map result = new HashMap();
    try {
      String url = "";
      if (type.equals("kilkbca")) {
        url = "https://api.sandbox.midtrans.com/v2/token?client_key=" + client_key
          + "&card_number=" + card_number + "&card_exp_month=" + card_exp_month
          + "&card_exp_year=" + card_exp_year + "&card_cvv=" + card_cvv;
      } else {
        System.out.println("===== client key : " + client_key);
        url = "https://api.sandbox.midtrans.com/v2/token?client_key=" + client_key
          + "&card_number=" + card_number;
      }

      Request request = new Request.Builder().url(url).build();

      String data = client.newCall(request).execute().body().string();
      JSONObject json = new JSONObject(data);
      ObjectMapper mapper = new ObjectMapper();
      result = mapper.readValue(json.toString(), new TypeReference<Map>() {
      });
    } catch (IOException | JSONException e) {
//      logger.error(stringUtil.getError(e));
    }

    return result;
  }

  public Map getExpiredTime(JSONObject body) {
    Map mResult = new HashMap();
    try {
      mResult = body.toMap();
      int day = 0;
      String payment_type = mResult.get("payment_type").toString();

      if (payment_type.equals("bank_transfer") || payment_type.equals("echannel") || payment_type.equals("cstore")) {
        day = 1;
        Date transaction_date = stringUtil.stringToDate(mResult.get("transaction_time").toString(), "yyyy-MM-dd hh:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.setTime(transaction_date);
        cal.add(Calendar.DATE, day);

        mResult.put("expired_time", cal.getTimeInMillis());
      }
    } catch (ParseException e) {

    }

    return mResult;
  }
}
