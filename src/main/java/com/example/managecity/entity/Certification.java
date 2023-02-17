package com.example.managecity.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "certification")
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_date")
    private LocalDateTime startEffectiveDate;

    @Column(name = "end_date")
    private LocalDateTime endEffectiveDate;
}
