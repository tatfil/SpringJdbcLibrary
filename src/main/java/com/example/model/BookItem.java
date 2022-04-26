package com.example.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

@javax.persistence.Entity
public class BookItem extends Book implements Entity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String barcode;

    private String status; //AVAILABLE, IN PROCESS, LIB USE ONLY, NOT AVAILABLE

    private Date borrowed;


    public BookItem() {
    }

    public BookItem(String title) {
        setTitle(title);
    }

    public BookItem(Integer id, String title) {
        setTitle(title);
        setId(id);
    }

    public BookItem(Integer isbn, String title, String barcode, String status) {
        setIsbn(isbn);
        setTitle(title);
        this.barcode = barcode;
        this.status = status;
    }


    public BookItem(int isbn, String title, String barcode, String status, Date borrowed) {
        this.setIsbn(isbn);
        this.setTitle(title);
        this.barcode = barcode;
        this.status = status;
        this.borrowed = borrowed;
    }

    public BookItem(Integer id, int isbn, String title, String barcode, String status, Date borrowed) {
        this.id = id;
        this.setIsbn(isbn);
        this.setTitle(title);
        this.barcode = barcode;
        this.status = status;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Date borrowed) {
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

    @Override
    public String toString() {
        return "BookItem{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                ", status='" + status + '\'' +
                ", borrowed=" + borrowed +
                '}';
    }
}
