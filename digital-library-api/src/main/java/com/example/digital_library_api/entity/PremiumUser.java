package com.example.digital_library_api.entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("PREMIUM")
@NoArgsConstructor
public class PremiumUser extends LibraryUser {

    // Пример специфичного поля
    @Column(name = "premium_until")
    private LocalDate premiumUntil;

    @Override
    public int getMaxBooksAllowed() {
        return 15;          // пример: премиум может взять больше
    }

    @Override
    public boolean hasPremiumFeatures() {
        return true;
    }

    // Конструктор с параметрами
    public PremiumUser(String id, String name) {
        this.setId(id);
        this.setName(name);
    }

    //
    public boolean isPremiumActive() {
        return premiumUntil != null && premiumUntil.isAfter(LocalDate.now());
    }
}