package com.example.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@javax.persistence.Entity
public class BookItem extends Book implements Entity<Integer> {

    public static final Integer CIRCULATION_PERIOD = 30;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private Integer barcode;

    private String status; //AVAILABLE, IN PROCESS, LIB USE ONLY, NOT AVAILABLE

    private LocalDate borrowed;


    public BookItem() {
    }

    public BookItem(String title) {
        setTitle(title);
    }

    public BookItem(Integer id, String title) {
        setTitle(title);
        setId(id);
    }

    public BookItem(Book book, Integer barcode, String status, LocalDate borrowed) {
        setIsbn(book.getIsbn());
        setTitle(book.getTitle());
        this.barcode = barcode;
        this.status = status;
        this.borrowed = borrowed;
    }

    public BookItem(Integer isbn, String title, Integer barcode, String status) {
        setIsbn(isbn);
        setTitle(title);
        this.barcode = barcode;
        this.status = status;
    }


    public BookItem(int isbn, String title, Integer barcode, String status, LocalDate borrowed) {
        this.setIsbn(isbn);
        this.setTitle(title);
        this.barcode = barcode;
        this.status = status;
        this.borrowed = borrowed;
    }

    public BookItem(Integer id, int isbn, String title, Integer barcode, String status, LocalDate borrowed) {
        this.id = id;
        this.setIsbn(isbn);
        this.setTitle(title);
        this.barcode = barcode;
        this.status = status;
        this.borrowed = borrowed;
    }

    public BookItem(Integer id, String title, LocalDate borrowed) {
        this.id = id;
        this.setTitle(title);
        this.borrowed = borrowed;
    }



    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBarcode() {
        return barcode;
    }

    public void setBarcode(Integer barcode) {
        this.barcode = barcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getBorrowed() {
        return (borrowed);
    }

    public void setBorrowed(LocalDate borrowed) {
        this.borrowed = borrowed;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookItem bookItem = (BookItem) o;
        return Objects.equals(id, bookItem.id) && Objects.equals(barcode, bookItem.barcode) && Objects.equals(status, bookItem.status) && Objects.equals(borrowed, bookItem.borrowed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, barcode, status, borrowed);
    }
}
