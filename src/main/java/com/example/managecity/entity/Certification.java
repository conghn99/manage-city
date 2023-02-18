package com.example.managecity.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    @JsonBackReference
    @OneToMany(mappedBy = "certification", cascade = CascadeType.ALL)
    private Set<EmployeeCertification> employeeCertifications = new HashSet<>();
}
