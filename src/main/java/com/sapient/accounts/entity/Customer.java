package com.sapient.accounts.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name="customer_id")
    private Long customerId;

    private String name;

    private String email;

    private String mobileNumber;
}
