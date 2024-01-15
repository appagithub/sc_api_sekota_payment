/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ebdesk.bigdata.api_sekota_payment.service;

import java.util.Map;
import org.json.JSONObject;

/**
 *
 * 
 */
public interface MidtransService {

  JSONObject charge(Map<String, Object> data);
  JSONObject getCardToken(Map<String, String> data);
  JSONObject getStatus(String orderId);
}
