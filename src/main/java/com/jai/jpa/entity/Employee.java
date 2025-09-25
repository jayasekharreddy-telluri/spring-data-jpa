package com.jai.jpa.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment in DB
    private Long id;

    @Column(nullable = false)
    private String name;

    private String role;

    private double salary;
}
