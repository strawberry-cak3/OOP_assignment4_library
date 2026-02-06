package com.example.digital_library_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "user_type",
        discriminatorType = DiscriminatorType.STRING
)
@Data
@NoArgsConstructor
public abstract class LibraryUser {

    @Id
    @Column(name = "id", length = 20)
    private String id;                  // например: "STU-123", "PRF-777"

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate = LocalDate.now();

    // Абстрактные методы
    public abstract int getMaxBooksAllowed();

    public abstract boolean hasPremiumFeatures();
}