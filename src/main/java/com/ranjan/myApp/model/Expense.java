package com.ranjan.myApp.model;
import jakarta.persistence.*;


@Entity
public class Expense {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title; // Changed from 'id' to 'title' for consistency with API
    private float amount;
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;



    public int getId() {
        return id;
    }

    public void setId(int id) {this.id = id; }

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public float getAmount() {return amount;}

    public void setAmount(float amount) {this.amount = amount;}

    public String getCategory() {return category;}

    public void setCategory(String category) {this.category = category;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}
}

