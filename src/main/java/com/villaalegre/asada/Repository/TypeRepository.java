package com.villaalegre.asada.Repository;

import com.villaalegre.asada.Models.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TypeRepository extends JpaRepository<Type, Long> {
    Type findByName(String name);
    List<Type> findByGroup(String group);

    @Query("SELECT e FROM Type e WHERE e.name = :name AND e.group = :group")
    Type findByNameAndGroup(String name, String group);
}
