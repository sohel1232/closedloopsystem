package com.card91.closedloopsystem.entity;

import com.card91.closedloopsystem.repository.TransactionRepository;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "settlement_sheet")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SettlementSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settlement_sheet_id")
    private Long settlementSheetId;

    @OneToOne
    @JoinColumn(name = "card_id")
    private Card card; //card from where to deduct

    @Column(name = "date")
    private Date date; //date for which the settlement sheet is being created

    @ManyToOne
    @JoinColumn(name = "bank_id")
    private Bank bank; // The bank associated with the settlement sheet

    @OneToMany(mappedBy = "settlementSheet")
    private List<Transaction> transactions; // Transactions for the card on the settlement date

}
