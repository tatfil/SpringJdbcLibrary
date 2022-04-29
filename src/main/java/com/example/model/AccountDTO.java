package com.example.model;

import java.util.Objects;
import java.util.Set;

public class AccountDTO {

    private Integer id;
    private String patronName;
    private String state;
    private Set<BookItem> borrowedItems;

    public AccountDTO() {
    }

    public AccountDTO(String patronName) {
        this.patronName = patronName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatronName() {
        return patronName;
    }

    public void setPatronName(String patronName) {
        this.patronName = patronName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Set<BookItem> getBorrowedItems() {
        return borrowedItems;
    }

    public void setBorrowedItems(Set<BookItem> borrowedItems) {
        this.borrowedItems = borrowedItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDTO that = (AccountDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(patronName, that.patronName) && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patronName, state);
    }
}
