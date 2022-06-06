package com.company.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode()
@Getter
@Setter
@Builder
@Entity
@Table(name = "card")
public class Card {
    @Id
    @Column(name = "card_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Long cardId;
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "card_amount")
    private Integer cardAmount;
    @OneToMany
    @JoinColumn(name = "card_id")
    private List<Payment> payments;
}
