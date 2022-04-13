package com.example.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@javax.persistence.Entity
public class BookItem extends Book implements Entity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String barcode;

    private String status; //AVAILABLE, IN PROCESS, LIB USE ONLY, NOT AVAILABLE

    private Date borrowed;

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer id) {

    }
}
