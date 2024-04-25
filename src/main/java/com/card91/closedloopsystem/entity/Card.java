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

    @Column(name = "lost_status")
    private String lost;

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

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public BigDecimal getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(BigDecimal cardBalance) {
        this.cardBalance = cardBalance;
    }

    public BigDecimal getTodaysSpent() {
        return todaysSpent;
    }

    public void setTodaysSpent(BigDecimal todaysSpent) {
        this.todaysSpent = todaysSpent;
    }

    public BigDecimal getDailySpentMaxLimit() {
        return dailySpentMaxLimit;
    }

    public void setDailySpentMaxLimit(BigDecimal dailySpentMaxLimit) {
        this.dailySpentMaxLimit = dailySpentMaxLimit;
    }

    public BigDecimal getMonthlySpentMaxLimit() {
        return monthlySpentMaxLimit;
    }

    public void setMonthlySpentMaxLimit(BigDecimal monthlySpentMaxLimit) {
        this.monthlySpentMaxLimit = monthlySpentMaxLimit;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public LocalDateTime getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(LocalDateTime expirydate) {
        this.expirydate = expirydate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", cardBalance=" + cardBalance +
                ", todaysSpent=" + todaysSpent +
                ", dailySpentMaxLimit=" + dailySpentMaxLimit +
                ", monthlySpentMaxLimit=" + monthlySpentMaxLimit +
                ", cardStatus='" + cardStatus + '\'' +
                ", expirydate=" + expirydate +
                ", user=" + user +
                ", bank=" + bank +
                ", transactions=" + transactions +
                '}';
    }
}
