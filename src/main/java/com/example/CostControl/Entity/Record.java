package com.example.CostControl.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RecordTable")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(referencedColumnName = "id", nullable = true)
    @JsonBackReference
    private User user;
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Category category;

    private Date recordCreationDateTime;

    private double expenseAmount;


    public Record(Long id, User user, Category category, Date recordCreationDateTime, double expenseAmount) {
        this.id = id;
        this.user = user;
        this.category = category;
        this.recordCreationDateTime = recordCreationDateTime;
        this.expenseAmount = expenseAmount;
    }

    public Record() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getRecordCreationDateTime() {
        return recordCreationDateTime;
    }

    public void setRecordCreationDateTime(Date recordCreationDatetime) {
        this.recordCreationDateTime = recordCreationDatetime;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

}
