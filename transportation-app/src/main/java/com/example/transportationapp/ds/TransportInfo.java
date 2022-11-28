package com.example.transportationapp.ds;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class TransportInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String orderId;
    @OneToMany(mappedBy = "transportInfo")
    private Set<Book> books=new HashSet<>();
    private double total;

    public TransportInfo() {
    }

    public TransportInfo(String name, String orderId, double total) {
        this.name = name;
        this.orderId = orderId;
        this.total = total;
    }

    public void addBook(Book book){
        book.setTransportInfo(this);
        books.add(book);
    }
}
