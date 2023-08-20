package com.villaalegre.asada.Users;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDTO> findAllUsers();

}
