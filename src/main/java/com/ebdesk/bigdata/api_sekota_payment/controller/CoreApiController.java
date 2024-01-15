package com.ebdesk.bigdata.api_sekota_payment.controller;

import com.ebdesk.bigdata.api_sekota_payment.serviceImpl.MidtransServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin
public class CoreApiController {

  @Autowired
  private MidtransServiceImpl midtransServiceImpl;

  @PostMapping(value = "/charge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> charge(@RequestBody Map<String, Object> data) {

//    data.put("credit_card", globalServiceImpl.setToken(data, "klikbca"));
    Map<String, Object> body = new HashMap<>(data);

    return new ResponseEntity<>(midtransServiceImpl.charge(body).toString(), HttpStatus.OK);
  }

  //  ========== Bank Transfer ==========
  @PostMapping(value = "/mandiri_bill_charge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> mandiriBillCharge(@RequestBody Map<String, Object> data) {

    Map<String, Object> body = new HashMap<>(data);

    return new ResponseEntity<>(midtransServiceImpl.charge(body).toString(), HttpStatus.OK);
  }

//  @CrossOrigin
  @PostMapping(value = "/bca_va_charge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> bcaVACharge(@RequestBody Map<String, Object> data) {

    Map<String, Object> body = new HashMap<>(data);

    return new ResponseEntity<>(midtransServiceImpl.charge(body).toString(), HttpStatus.OK);
  }

//  @CrossOrigin
  @PostMapping(value = "/bni_va_charge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> bniVACharge(@RequestBody Map<String, Object> data) {

    Map<String, Object> body = new HashMap<>(data);

    return new ResponseEntity<>(midtransServiceImpl.charge(body).toString(), HttpStatus.OK);
  }

  //  ========== internet banking ==========
  @PostMapping(value = "/bca_klikpay_charge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> bcaKlikpayCharge(@RequestBody Map<String, Object> data) {

    Map<String, Object> body = new HashMap<>(data);

    return new ResponseEntity<>(midtransServiceImpl.charge(body).toString(), HttpStatus.OK);
  }

  @PostMapping(value = "/klikbca_charge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> klikBcaCharge(@RequestBody Map<String, Object> data) {

    Map<String, Object> body = new HashMap<>(data);

    return new ResponseEntity<>(midtransServiceImpl.charge(body).toString(), HttpStatus.OK);
  }

  @PostMapping(value = "/mandiri_clickpay_charge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> mandiriClickpayCharge(@RequestBody Map<String, Object> data) {

//    data.put("mandiri_clickpay", globalServiceImpl.setToken(data, "mandiri_clickpay"));
    Map<String, Object> body = new HashMap<>(data);

    return new ResponseEntity<>(midtransServiceImpl.charge(body).toString(), HttpStatus.OK);
  }

  @PostMapping(value = "/epay_bri_charge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> epayBriCharge(@RequestBody Map<String, Object> data) {

    Map<String, Object> body = new HashMap<>(data);

    return new ResponseEntity<>(midtransServiceImpl.charge(body).toString(), HttpStatus.OK);
  }

  @PostMapping(value = "/cimb_clicks_charge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> cimbClicksCharge(@RequestBody Map<String, Object> data) {

    Map<String, Object> body = new HashMap<>(data);

    return new ResponseEntity<>(midtransServiceImpl.charge(body).toString(), HttpStatus.OK);
  }
  
  @PostMapping(value = "/danamon_online_banking_charge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> danamonOnlineBankingCharge(@RequestBody Map<String, Object> data) {

    Map<String, Object> body = new HashMap<>(data);

    return new ResponseEntity<>(midtransServiceImpl.charge(body).toString(), HttpStatus.OK);
  }

//  ========== e wallet ==========
  @PostMapping(value = "/mandiri_ecash_charge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> mandirieCashCharge(@RequestBody Map<String, Object> data) {

    Map<String, Object> body = new HashMap<>(data);

    return new ResponseEntity<>(midtransServiceImpl.charge(body).toString(), HttpStatus.OK);
  }

  @PostMapping(value = "/gopay_charge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> goPayCharge(@RequestBody Map<String, Object> data) {

    Map<String, Object> body = new HashMap<>(data);

    return new ResponseEntity<>(midtransServiceImpl.charge(body).toString(), HttpStatus.OK);
  }

//  ========== over the counter ==========
  @PostMapping(value = "/alfamart_charge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> alfamartCharge(@RequestBody Map<String, Object> data) {

    Map<String, Object> body = new HashMap<>(data);

    return new ResponseEntity<>(midtransServiceImpl.charge(body).toString(), HttpStatus.OK);
  }

  @PostMapping(value = "/indomaret_charge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> indomaretCharge(@RequestBody Map<String, Object> data) {

    Map<String, Object> body = new HashMap<>(data);

    return new ResponseEntity<>(midtransServiceImpl.charge(body).toString(), HttpStatus.OK);
  }

//  ========== cardless credit ==========
  @PostMapping(value = "/akulaku_charge", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> akulakuCharge(@RequestBody Map<String, Object> data) {

    Map<String, Object> body = new HashMap<>(data);

    return new ResponseEntity<>(midtransServiceImpl.charge(body).toString(), HttpStatus.OK);
  }

  @PostMapping(value = "/get_status", produces = MediaType.APPLICATION_JSON_VALUE)
  public void getStatus() {

    midtransServiceImpl.getStatus("order-101c-123429");
//    return new ResponseEntity<>(, HttpStatus.OK);
  }

  @PostMapping(value = "/get_token", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> getToken(@RequestBody Map<String, String> data) {

    Map<String, String> body = new HashMap<>(data);
    return new ResponseEntity<>(midtransServiceImpl.getCardToken(body).toString(), HttpStatus.OK);
  }

}
