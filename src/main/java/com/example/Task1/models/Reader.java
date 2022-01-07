package com.example.Task1.models;

import com.example.Task1.validator.Validator;

import java.sql.Date;
import java.util.ArrayList;

public class Reader {



    public Validator validator;
    public Long id;
    public String firstName;
    public String lastName;
    public String patronymic;
    public String passportNumber;
    public String address;
    public String email;
    public Date date;
    public Integer readerPhotoId;

    public Reader(){
        validator = new Validator();
    }


    public Integer getReaderPhotoId() {
        return readerPhotoId;
    }

    public void setReaderPhotoId(Integer readerPhotoId) {
        this.readerPhotoId = readerPhotoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        boolean valid = validator.validateName(firstName);
        if(valid)
            this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        boolean valid = validator.validateName(lastName);
        if(valid)
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {

        boolean valid = validator.validateName(patronymic);
        if(valid)
            this.patronymic = patronymic;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
       /* boolean valid = validator.validatePassport(email);
        if(valid)*/
        this.passportNumber = passportNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        boolean valid = validator.validateEmail(email);
        if(valid)
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
