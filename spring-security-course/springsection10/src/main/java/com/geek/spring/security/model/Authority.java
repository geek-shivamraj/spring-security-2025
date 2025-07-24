package com.geek.spring.security.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name="authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    /**
     * @ManyToOne - Multiple authority records mapped to one customer
     * @JoinColumn - customer_id i.e., primary key in the customer table is the
     * foreign key in the authority table
     */
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

}
