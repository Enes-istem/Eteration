package com.eteration.simplebanking.controller;

import java.util.UUID;

public class TransactionStatus {

    private String status; 
    private String approvalCode; 

    public TransactionStatus() {
    }

    public TransactionStatus(String status) {
        this.status = status;
        this.approvalCode = generateApprovalCode();
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
    
    private String generateApprovalCode() {
        return UUID.randomUUID().toString();
    }
    
}