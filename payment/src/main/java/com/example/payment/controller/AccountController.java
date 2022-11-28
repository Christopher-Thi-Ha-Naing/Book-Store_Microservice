package com.example.payment.controller;

import com.example.payment.dao.AccountDao;
import com.example.payment.ds.Account;
import com.example.payment.ds.AccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/payment")
public class AccountController {
    @Autowired
    private AccountDao accountDao;

    //curl -X POST -H "Content-Type: application/json" -d '{"name":"Thuza Nwe","totalAmount":78.5,"accountNumber":"ThuzaNwe15310"}' localhost:9000/payment/checkout
    @Transactional
    @PostMapping(value = "/checkout",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity checkout(@RequestBody AccountInfo accountInfo){
        Optional<Account> customerAccountOptional=accountDao.findAccountByNameAndAccountNumber(accountInfo.getName(),accountInfo.getAccountNumber());
        if (!customerAccountOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Account userAccount=customerAccountOptional.get();
        Account ownerAccount=accountDao.findById(2).get();
        if (accountInfo.getTotalAmount()>userAccount.getAmount()){
            return ResponseEntity.badRequest().body("Insufficient Amount");
        }else{
            transferAmount(accountInfo.getTotalAmount(), userAccount, ownerAccount);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    private void transferAmount(double totalAmount, Account userAccount, Account ownerAccount) {
        userAccount.setAmount(userAccount.getAmount()-totalAmount);
        ownerAccount.setAmount(ownerAccount.getAmount()+totalAmount);
    }

    public  double toDouble(String amount){
        return Double.parseDouble(amount);
    }

    //
    //curl -X POST -H "Content-Type: application/json" -d'{"name":"Thuza Nwe","amount":"500000","accountType":"CASH"}' localhost:9000/payment/register
    @PostMapping(value = "/register",consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity register(@RequestBody Account account){
        account.setAccountNumber(generateId(account.getName()));
        return ResponseEntity.ok(accountDao.save(account));

    }

    public String generateId(String name){
        Random random=new Random();
        return removeWhiteSpace(name)+((random.nextInt(10000)+10000));
    }

    private static String removeWhiteSpace(String name) {
        String n1="";
        if(name.contains(" ")){
            String[] names= name.split(" ");
            for(String n:names){
                n1+=n;
            }
        }else {
            n1= name;
        }
        return n1.trim();
    }
}
