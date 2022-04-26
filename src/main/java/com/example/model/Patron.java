package com.example.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@javax.persistence.Entity
public class Patron implements Entity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String address;


    public Patron() {
    }

    public Patron(Integer id) {
        this.id = id;
    }

    public Patron(String name) {
        this.name = name;
    }

    public Patron(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Patron(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patron patron = (Patron) o;
        return Objects.equals(id, patron.id) && Objects.equals(name, patron.name) && Objects.equals(address, patron.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address);
    }

    @Override
    public String toString() {
        return "Patron{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

