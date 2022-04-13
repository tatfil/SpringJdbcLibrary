package com.example.exception;

import com.example.model.Entity;

public class DAOException extends Exception {
    public DAOException(String s, Entity entity) {

    }

    public DAOException(Exception exception, Entity entity) {

    }

    public DAOException(Exception e, Integer id) {

    }

    public DAOException(Exception e) {

    }


    public DAOException(Exception e, String string) {
    }

    public DAOException(Exception e, Entity entity, Integer integer) {
    }

    public DAOException(Exception e, Entity entity, String string) {
    }

    public DAOException(String s, Integer integer) {

    }

    public DAOException(String s) {
        super(s);
    }
}
