package com.card91.closedloopsystem.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Long bankId;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "address")
    private String address;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "balance")
    private BigDecimal bankBalance;

    @OneToMany(mappedBy = "bank", cascade = CascadeType.ALL)
    private List<Card> cardsIssued;

    @ManyToMany
    @JoinTable(
            name = "merchant_bank",
            joinColumns = @JoinColumn(name = "bank_id"),
            inverseJoinColumns = @JoinColumn(name = "merchant_id")
    )
    private List<Merchant> registeredMerchants;
}
