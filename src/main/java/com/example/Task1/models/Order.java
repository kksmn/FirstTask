package com.example.Task1.models;

import java.util.Calendar;

public class Order {
    public Long id;
    public Long ReaderId;
    public Double price;
    public Long copy_id;
    public Double fine;
    public Calendar date;
    public Double rating;

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReaderId() {
        return ReaderId;
    }

    public void setReaderId(Long readerId) {
        ReaderId = readerId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getCopy_id() {
        return copy_id;
    }

    public void setCopy_id(Long copy_id) {
        this.copy_id = copy_id;
    }

    public Double getFine() {
        return fine;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
