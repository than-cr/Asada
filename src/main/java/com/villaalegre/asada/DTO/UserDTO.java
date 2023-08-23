package com.villaalegre.asada.DTO;

import com.villaalegre.asada.Models.Type;
import com.villaalegre.asada.Services.TypeService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Setter
public class UserDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String motherLastName;
    private String email;
    private String phoneNumber;
    private String role;
    private String status;
}
