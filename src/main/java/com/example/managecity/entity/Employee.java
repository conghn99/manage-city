package com.example.managecity.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "code")
    @NotBlank(message = "code must not blank")
    @Size(min = 6, max = 10, message = "Code must be be between 6 and 10 character")
    @UniqueElements(message = "Code already exist")
    private String code;

    @Column(name = "name")
    @NotBlank(message = "name must not blank")
    private String name;

    @Column(name = "email")
    @Email
    @NotBlank(message = "email must not blank")
    private String email;

    @Column(name = "age")
    @Positive(message = "age must be greater than 0")
    private int age;

    @Column(name = "phone")
    @NotBlank(message = "phone must not blank")
    @Digits(integer = 11, fraction = 0, message = "invalid phone number")
    private String phone;
}
