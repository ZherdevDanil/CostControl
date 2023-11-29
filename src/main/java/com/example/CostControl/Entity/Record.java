package com.example.CostControl.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "RecordTable")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long userId;

    private long categoryId;

    private Date recordCreationDateTime;

    private double expenseAmount;

    public Record(long id, long userId, long categoryId, Date recordCreationDateTime, double expenseAmount) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.recordCreationDateTime = recordCreationDateTime;
        this.expenseAmount = expenseAmount;
    }

    public Record() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
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
