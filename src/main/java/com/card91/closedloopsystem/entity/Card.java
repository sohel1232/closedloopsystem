package com.card91.closedloopsystem.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_id")
    private Long cardId;

    @Column(name = "balance")
    private BigDecimal cardBalance;

    @Column(name = "today_spent")
    private BigDecimal todaysSpent;

    @Column(name = "daily_spent_limit")
    private BigDecimal dailySpentMaxLimit;

    @Column(name = "monthly_spend_limit")
    private BigDecimal monthlySpentMaxLimit;

    @Column(name = "card_status")
    private String cardStatus;

    @Column(name = "expiry")
    private LocalDateTime expirydate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}
