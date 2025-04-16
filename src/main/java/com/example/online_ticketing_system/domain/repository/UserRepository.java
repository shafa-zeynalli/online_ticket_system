package com.example.online_ticketing_system.domain.repository;

import com.example.online_ticketing_system.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    User save(User user);
    void deleteById(Long id);
    List<User> findAll();
}



//UserRepository domain-də olur (port funksiyası görür)
//Infrastructure ona implement yazır
//Application isə istifadə edir
//Application yalnız use-case yazır və bu interfeysi istifadə edir.
//Infrastructure bu interfeysi implement edir.

//Domain/business qatında (UserService) yalnız UserRepository-dən asılılıq var.
//Əgər sabahlar UserJpaRepository əvəzinə MongoRepository və ya REST API
// istifadə etmək istəsən, sadəcə UserRepositoryImpl-i dəyişərsən, digər qatlara toxunmazsan.
