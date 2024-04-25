package com.card91.closedloopsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="merchant")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "merchant_id")
    private Long merchantId;

    @Column(name = "merchant_name")
    private String merchantName;

    @Column(name = "merchant_phonenumber")
    private String phoneNumber;

    @Column(name = "machine_status")
    private String machineStatus;

    @OneToMany(mappedBy = "merchant", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @ManyToMany
    @JoinTable(
            name = "merchant_bank",
            joinColumns = @JoinColumn(name = "merchant_id"),
            inverseJoinColumns = @JoinColumn(name = "bank_id")
    )
    private List<Bank> banks;

    @OneToMany(mappedBy = "merchant")
    private List<Request> requestList;
}
