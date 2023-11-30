package com.example.CostControl.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
@Table(name = "CategoryTable")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Category name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "must contain only letters")
    private String categoryName;
    /*
    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<Record> records;*/

    public Category(Long id, String categoryName/*,List<Record> records*/) {
        this.id = id;
        this.categoryName = categoryName;
        //this.records = records;
    }

    public Category() {
    }
    /*
    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /*@Override
    public String toString() {
        return "id=" + id +
                ", categoryName='" + categoryName;
    }*/
}
