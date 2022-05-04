package com.example.model;


import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class BookItemDTO {

    private Integer id;
    private String authorName;
    private int isbn;
    private String title;
    private Integer barcode;
    private String status;
    private LocalDate borrowed;
    private LocalDate dueToDate;

    public BookItemDTO() {
    }

    public BookItemDTO(Integer id, String authorName, int isbn, String title, Integer barcode, String status, LocalDate borrowed, LocalDate dueToDate) {
        this.id = id;
        this.authorName = authorName;
        this.isbn = isbn;
        this.title = title;
        this.barcode = barcode;
        this.status = status;
        this.borrowed = borrowed;
        this.dueToDate = dueToDate;
    }

    public BookItemDTO(Integer id, int isbn, String title, Integer barcode, String status, LocalDate borrowed) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.barcode = barcode;
        this.status = status;
        this.borrowed = borrowed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return borrowed;
    }

    public void setBorrowed(LocalDate borrowed) {
        this.borrowed = borrowed;
    }

    public LocalDate getDueToDate() {
        return dueToDate;
    }

    public void setDueToDate(LocalDate dueToDate) {
        this.dueToDate = dueToDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookItemDTO that = (BookItemDTO) o;
        return isbn == that.isbn && Objects.equals(id, that.id) && Objects.equals(authorName, that.authorName) && Objects.equals(title, that.title) && Objects.equals(barcode, that.barcode) && Objects.equals(status, that.status) && Objects.equals(borrowed, that.borrowed) && Objects.equals(dueToDate, that.dueToDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorName, isbn, title, barcode, status, borrowed, dueToDate);
    }
}
