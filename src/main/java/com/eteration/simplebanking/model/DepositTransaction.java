
package com.eteration.simplebanking.model;

import java.util.UUID;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;

@Entity
public class DepositTransaction extends Transaction {
	private String type;

    public DepositTransaction() {
    }

    public DepositTransaction(double amount) {
        super(amount);
        this.type = getType();
    }
    
    

    @Override
    public String getType() {
        return "DepositTransaction";
    }
    
    private String generateApprovalCode() {
       
        return UUID.randomUUID().toString();
    }
}
