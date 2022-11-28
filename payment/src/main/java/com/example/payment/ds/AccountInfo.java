package com.example.payment.ds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class AccountInfo {

    private String name;
    private double totalAmount;
    private String accountNumber;

    public AccountInfo() {
    }
}
