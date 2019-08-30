package com.vilderlee.mongodb.domain;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@ToString
@Document
public class FundIn {

  private String txno;
  private String txtime;
  private String txtype;
  private String institutionid;
  private String sourcetxno;
  private long amount;
  private String note;
  private long accounttype;
  private String bankid;
  private String accountname;
  private String accountnumber;
  private String branchname;
  private String province;
  private String city;
  private String statusmanager;
  private String responsesubcode;
  private String identificationtype;
  private String identificationnumber;
  private String contractuserid;
  private String phonenumber;
  private String email;
  private long status;
  private String banktime;
  private String responsecode;
  private String responsemessage;
  private String transactor;
  private String transacttime;
  private String checker;
  private String checktime;
  private String gatheringbankid;
  private String gatheringbankway;
  private String banktxno;
  private String bankbatchno;
  private String bankitemno;
  private String bankcredentialitemno;
  private String sendtime;
  private long querycount;
  private String sourcetxsn;
  private String merchantid;
  private String merchantname;
  private String businesstype;
  private String sourceresponsecode;
  private String sourceresponsemessage;
  private String merchantabbr;
  private String mcc;
  private String mccdescription;
  private String cardmediatype;
  private String banknobypbc;
  private String phonenumberencrypt;
  private String identificationnumberencrypt;
  private String accountnameencrypt;
  private String accountnumberencrypt;
  private String institutionparentid;


}
