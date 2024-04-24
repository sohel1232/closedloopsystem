package com.card91.closedloopsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "merchant_bank")
public class MerchantBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "merchant_bank_id")
    private Long merchantBankId;

    @Column(name = "merchant_id")
    private Long merchantId;

    @Column(name = "bank_id")
    private Long bankId;

    @Column(name = "mdr")
    private Double mdr;

}
