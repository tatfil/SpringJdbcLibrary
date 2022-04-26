package com.example.model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Objects;

@javax.persistence.Entity
public class Account implements Entity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer patronId;

    private String state;

    public Account() {
    }

    public Account(Integer patronId, String state) {
        this.patronId = patronId;
        this.state = state;
    }

    public Account(Integer id, Integer patronId, String state) {
        this.id = id;
        this.patronId = patronId;
        this.state = state;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPatronId() {
        return patronId;
    }

    public void setPatronId(Integer patronId) {
        this.patronId = patronId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(patronId, account.patronId) && Objects.equals(state, account.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patronId, state);
    }
}
