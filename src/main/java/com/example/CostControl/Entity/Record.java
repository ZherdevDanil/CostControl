package com.example.CostControl.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "RecordTable")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long categoryId;

    private Date recordCreationDateTime;

    private double expenseAmount;

    public Record(Long id, Long userId, Long categoryId, Date recordCreationDateTime, double expenseAmount) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
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

    @Override
    public String toString() {
        return "id=" + id +
                ", userId=" + userId +
                ", categoryId=" + categoryId +
                ", recordCreationDateTime=" + recordCreationDateTime +
                ", expenseAmount=" + expenseAmount;
    }
}
