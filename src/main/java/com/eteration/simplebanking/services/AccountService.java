
package com.eteration.simplebanking.services;

import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.exception.AccountNotFoundException;
import com.eteration.simplebanking.model.Account;
import com.eteration.simplebanking.model.PhoneBillPaymentTransaction;
import com.eteration.simplebanking.model.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.Transaction;
import com.eteration.simplebanking.model.WithdrawalTransaction;
import com.eteration.simplebanking.repositories.AccountRepository;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  AccountService {
	Gson gson = new Gson();

    @Autowired
    private AccountRepository accountRepository;
    


   
    public Account createAccount(String owner, String accountNumber) {
        Account account = new Account(owner, accountNumber);
        return accountRepository.save(account);
    }


    
    public List<Transaction> getTransactions(String accountNumber) {
    	Account account = findAccount(accountNumber);
        if (account != null) {
            return account.getTransactions();
        }
        return null;
    }


    
    
    public TransactionStatus credit(String accountNumber, Transaction transactions) {
  	Account account = findAccount(accountNumber);
      if (account != null) {
          Transaction transaction = new DepositTransaction(transactions.getAmount());
          account.setBalance(account.getBalance() + transactions.getAmount());

        
          TransactionStatus transactionStatus = new TransactionStatus("OK");
          System.out.println(transactionStatus.getApprovalCode());
          transaction.setApprovalCode(transactionStatus.getApprovalCode());
          List<Transaction> transasctionList = new ArrayList<>();
          transasctionList.add(transaction);
        
          //System.out.println("account1 : " + gson.toJson(account));
          //System.out.println("transaction1 : " + gson.toJson(transaction));
          account.setTransactions(transasctionList);
          account.post(transaction);
          transaction.setAccount(account);
        
        
          accountRepository.save(account);
        
          return transactionStatus;
      }
      return null;
  }

    

    
    public TransactionStatus debit(String accountNumber, Transaction transactions) throws InsufficientBalanceException {
    	Transaction transaction = null;
  	Account account = findAccount(accountNumber);
      if (account != null) {
          if (account.getBalance() >= transactions.getAmount()) {
        	  System.out.println("tip1 : " +transactions.getType() );
        	  if( transactions.getType() == "PhoneBillPaymentTransaction") {
        		   transaction = new PhoneBillPaymentTransaction("","05523691580",transactions.getAmount());
        	  }else if(transactions.getType() == "WithdrawalTransaction") {
        		   transaction = new WithdrawalTransaction(transactions.getAmount());
        	  }
        	
              account.setBalance(account.getBalance() - transactions.getAmount());
              TransactionStatus transactionStatus = new TransactionStatus("OK");
          
              transaction.setApprovalCode(transactionStatus.getApprovalCode());
              List<Transaction> transasctionList = new ArrayList<>();
              transasctionList.add(transaction);
              
              account.setTransactions(transasctionList);
              account.post(transaction);
              transaction.setAccount(account);
              accountRepository.save(account);
              return transactionStatus;
          }
          throw new InsufficientBalanceException("Yetersiz bakiye");
      }
        return null;
  }
    
    public Account findAccount(String accountNumber) {
    	Account account = accountRepository.findByAccountNumber(accountNumber);
    	if(account != null) {
    		return account;
    	}
    	throw new AccountNotFoundException("Hesap bulunamadı");
    }
    
    public List<Account> findAccount2(String accountNumber) {
    	return accountRepository.findAll();
    }
    
    
}

