package com.company.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EqualsAndHashCode()
@Table(name = "contract")
public class Contract {
    @Id
    @Column(name = "contract_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Long contractId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String subject;
    @Column(nullable = false)
    private BigDecimal amount;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDate date;
    @Column(nullable = false)
    private String comment;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @OneToMany
    @JoinColumn(name = "contract_id")
    private List<Payment> payments;
}