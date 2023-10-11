
//package com.eteration.simplebanking.model;
//
//import javax.persistence.DiscriminatorValue;
//import javax.persistence.Embeddable;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//
//
//@Entity
//public class PhoneBillPaymentTransaction extends Transaction {
//	
//
//	
//
//	private String payee;
//    private String type;
//    
//    private String phoneNumber;
//
//    public String getPhoneNumber() {
//		return phoneNumber;
//	}
//
//	public void setPhoneNumber(String phoneNumber) {
//		this.phoneNumber = phoneNumber;
//	}
//
//	public PhoneBillPaymentTransaction() {
//    }
//
//    public PhoneBillPaymentTransaction(String payee, String phoneNumber, double amount) {
//        super(amount);
//        this.payee = payee;
//        this.phoneNumber = phoneNumber;
//        this.type = getType();
//    }
//
//    public String getPayee() {
//        return payee;
//    }
//
//    public void setPayee(String payee) {
//        this.payee = payee;
//    }
//
//    @Override
//    public String getType() {
//        return "PhoneBillPaymentTransaction";
//    }
//}








package com.eteration.simplebanking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class PhoneBillPaymentTransaction extends WithdrawalTransaction {
	

	

	private String payee;
    private String type;
    
    private String phoneNumber;

    public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public PhoneBillPaymentTransaction() {
    }

    public PhoneBillPaymentTransaction(String payee, String phoneNumber, double amount) {
        super(amount);
        this.payee = payee;
        this.phoneNumber = phoneNumber;
        this.type = getType();
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    @Override
    public String getType() {
        return "PhoneBillPaymentTransaction";
    }
}
