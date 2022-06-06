package com.company.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@Getter
@Setter
@Builder
@Entity
@Table(name = "payment")
public class Payment implements Serializable {
    @Id
    @Column(name = "payment_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Long paymentId;
    @Column(name = "amount_of_money")
    private BigDecimal amountOfMoney;
    @Column(name = "times")
    private Timestamp timestamp;
    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;
    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private Contract contract;
}