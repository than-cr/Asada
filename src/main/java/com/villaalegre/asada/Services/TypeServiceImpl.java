package com.villaalegre.asada.Services;

import com.villaalegre.asada.Models.Type;
import com.villaalegre.asada.Repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Override
    public Optional<Type> findById(long id) {
        return typeRepository.findById(id);
    }

    @Override
    public Type findByName(String name) { return typeRepository.findByName(name); }

    @Override
    public List<Type> findAll() { return typeRepository.findAll(); }

    @Override
    public List<Type> findByGroup(String group) { return typeRepository.findByGroup(group); }

    @Override
    public Type findByNameAndGroup(String name, String group) { return typeRepository.findByNameAndGroup(name, group); }
}
