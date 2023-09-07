package com.villaalegre.asada.Services;

import com.villaalegre.asada.DTO.UserDTO;
import com.villaalegre.asada.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    List<User> findAll();

    Page<User> findAll(Pageable pageable);

    void save(User user);

    Optional<User> findById(Long userId);
    Optional<User> findByUsername(String username);

    UserDTO registerNewUserOrUpdate(UserDTO userDTO) throws Exception;

    UserDTO convertToDTO(User user);
    User convertToEntity(UserDTO userDTO);
}
