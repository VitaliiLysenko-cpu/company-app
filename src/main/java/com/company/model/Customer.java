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
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "customer_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native", strategy = "native")
    private Long customerId;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<Contract> contracts;
}