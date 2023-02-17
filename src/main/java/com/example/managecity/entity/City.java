package com.example.managecity.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "city")
public class City{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<District> districts;

    @JsonBackReference
    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST})
    private List<Employee> employees;

    @JsonBackReference
    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Certification> certifications;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "certification_id")
    private Certification certification;

    @PreRemove
    public void preRemove() {
        for (Employee e : employees) {
            e.setCity(null);
        }
    }
}
