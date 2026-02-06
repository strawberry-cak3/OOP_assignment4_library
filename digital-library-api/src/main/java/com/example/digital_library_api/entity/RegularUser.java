package com.example.digital_library_api.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("REGULAR")
@NoArgsConstructor
public class RegularUser extends LibraryUser {

    // private int borrowedBooksCount;  // например

    @Override
    public int getMaxBooksAllowed() {
        return 5;           // пример: обычный пользователь может взять 5 книг
    }

    @Override
    public boolean hasPremiumFeatures() {
        return false;
    }

    // Конструктор с параметрами
    public RegularUser(String id, String name) {
        this.setId(id);
        this.setName(name);
    }
}