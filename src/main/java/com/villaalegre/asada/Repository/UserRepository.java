package com.villaalegre.asada.Repository;

import com.villaalegre.asada.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT e FROM User e WHERE e.username = :username")
    Optional<User> findByUsername(String username);
}
