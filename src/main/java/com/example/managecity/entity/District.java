package com.example.managecity.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private City city;

    @JsonBackReference
    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Ward> wards;

    @JsonBackReference
    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
    private List<Employee> employees;

    @PreRemove
    public void preRemove() {
        for (Employee e : employees) {
            e.setDistrict(null);
        }
    }
}
