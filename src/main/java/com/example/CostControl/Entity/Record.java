package com.example.CostControl.Entity;

import java.util.Date;

public class Record {
    private long id;

    private long userId;

    private long categoryId;

    private Date recordCreationDatetime;

    private int expenseAmount;

    public Record(long id, long userId, long categoryId, Date recordCreationDatetime, int expenseAmount) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.recordCreationDatetime = recordCreationDatetime;
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

    public Date getRecordCreationDatetime() {
        return recordCreationDatetime;
    }

    public void setRecordCreationDatetime(Date recordCreationDatetime) {
        this.recordCreationDatetime = recordCreationDatetime;
    }

    public int getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(int expenseAmount) {
        this.expenseAmount = expenseAmount;
    }
}
