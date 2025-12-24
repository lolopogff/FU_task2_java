package com.fu.project.task1.demo;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
@Data
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // "ADMIN" или "USER"
}