package com.fu.project.task1.demo;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Entity
@Table(name = "tours")
@Data
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "departure_date", nullable = false)
    private String departureDate;
}
