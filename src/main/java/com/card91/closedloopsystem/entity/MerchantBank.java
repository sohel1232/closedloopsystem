package com.card91.closedloopsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "merchant_bank")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "merchant_bank_id")
    private Long merchantBankId;

    @Column(name = "merchant_id")
    private Long merchantId;

    @Column(name = "bank_id")
    private Long bankId;

    @Column(name = "merchant_balance")
    private BigDecimal merchantBalance;

    @Column(name = "mdr")
    private Double mdr;

}
