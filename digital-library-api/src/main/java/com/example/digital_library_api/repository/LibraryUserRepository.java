package com.example.digital_library_api.repository;

import com.example.digital_library_api.entity.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser, String> {

    // Здесь можно (но не обязательно) добавить свои специальные методы поиска
    // Примеры

    // List<LibraryUser> findByNameContainingIgnoreCase(String namePart);

    // Optional<LibraryUser> findByName(String exactName);

    // boolean existsByName(String name);

    // @Query("SELECT u FROM LibraryUser u WHERE TYPE(u) = RegularUser")
    // List<LibraryUser> findAllRegularUsers();

    // @Query("SELECT u FROM LibraryUser u WHERE TYPE(u) = PremiumUser")
    // List<LibraryUser> findAllPremiumUsers();
}