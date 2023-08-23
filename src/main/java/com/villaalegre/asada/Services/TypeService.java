package com.villaalegre.asada.Services;

import com.villaalegre.asada.Models.Type;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TypeService {
    Optional<Type> findById(long id);
    Type findByName(String name);

    List<Type> findAll();
    List<Type> findByGroup(String group);

    Type findByNameAndGroup(String name, String group);
}
