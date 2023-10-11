
package com.eteration.simplebanking.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity(name = "Account")
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String owner;
    private String accountNumber;
    private double balance;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

//    @Embedded

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "account",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Transaction> transactions;
    
    

    public Account() {
    	
    }

    public Account(String owner, String accountNumber) {
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.createDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Date getCreateDate() {
        return createDate;
    }

 
    
    public void post(Transaction transaction) {
    	if(this.transactions == null) {
    		this.transactions = new ArrayList<>();
    	}
        this.transactions.add(transaction);
       // transaction.setAccount(this);
    }

    @Embedded
    public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	

	public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }
}
