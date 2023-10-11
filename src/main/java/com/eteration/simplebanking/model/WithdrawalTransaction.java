package com.eteration.simplebanking.model;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WithdrawalTransaction extends Transaction {
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//	public Long getId() {
//		return id;
//	}
	
	private String type;


    public WithdrawalTransaction() {
    }

    public WithdrawalTransaction(double amount) {
        super(amount);
        this.type = getType();
    }

    @Override
    public String getType() {
        return "WithdrawalTransaction";
    }
}

