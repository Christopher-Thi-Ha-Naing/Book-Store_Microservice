package com.example.payment;

import com.example.payment.dao.AccountDao;
import com.example.payment.ds.Account;
import com.example.payment.ds.AccountType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class PaymentApplication {

    @Autowired
    private AccountDao accountDao;

    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }

    @Bean
    @Profile("dev")
    public ApplicationRunner runner(){
        return args -> {
            Account customerAccount=new Account("Thaw Thaw",300000, AccountType.CASH);
            Account ownerAccount=new Account("John Doe",1000,AccountType.CASH);
            accountDao.save(customerAccount);
            accountDao.save(ownerAccount);
        };
    }

}
