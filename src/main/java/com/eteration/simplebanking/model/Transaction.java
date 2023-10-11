
package com.eteration.simplebanking.model;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

@DiscriminatorColumn(name = "transaction_type")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
      @JsonSubTypes.Type(value = DepositTransaction.class, name = "DepositTransaction"),
      @JsonSubTypes.Type(value = WithdrawalTransaction.class, name = "WithdrawalTransaction"),
      @JsonSubTypes.Type(value = PhoneBillPaymentTransaction.class, name = "PhoneBillPaymentTransaction")
})
public abstract class Transaction implements Serializable {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @JsonIgnore
	 private Long id;

//    private String status;
    private String approvalCode;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonBackReference
    private Account account;

 
    public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
    
 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	private Date date;

    private double amount;

    protected Transaction() {
    }

    public Transaction(double amount) {
        this.amount = amount;
        this.date = new Date();
    }

  

    public Date getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public abstract String getType();
}
