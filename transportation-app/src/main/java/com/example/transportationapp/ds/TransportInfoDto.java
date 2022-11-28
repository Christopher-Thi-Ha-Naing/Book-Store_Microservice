package com.example.transportationapp.ds;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class TransportInfoDto {

    private String name;
    private String orderId;
    private double total;

    private List<BookDto> books=new ArrayList<>();

    public TransportInfoDto() {
    }

    public TransportInfoDto(String name, String orderId, double total) {
        this.name = name;
        this.orderId = orderId;
        this.total = total;
    }

}
