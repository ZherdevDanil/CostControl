package com.example.CostControl.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int moneyAmount;

    @OneToOne
    private User user;

}
