package com.villaalegre.asada.Services;

import com.villaalegre.asada.DTO.UserDTO;
import com.villaalegre.asada.Models.Role;
import com.villaalegre.asada.Models.Type;
import com.villaalegre.asada.Models.User;
import com.villaalegre.asada.Repository.UserRepository;
import com.villaalegre.asada.Utilities.CommonValues;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TypeService typeService;

    @Override
    public Optional<User> findByUsername(String username) { return userRepository.findByUsername(username); }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void save(User user) { userRepository.save(user); }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserDTO registerNewUserOrUpdate(UserDTO userDTO) throws Exception {
        Optional<User> userFound = userRepository.findByUsername(userDTO.getUsername());

        User user = convertToEntity(userDTO);
        if (userFound.isEmpty()) {
            user.setPassword(passwordEncoder.encode(CommonValues.DEFAULT_PASSWORD));
        }
        else {
            User existingUser = userFound.get();
            user.setId(existingUser.getId());
            user.setPassword(existingUser.getPassword());
        }

        this.save(user);

        return userDTO;
    }

    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setStatus(user.getStatus().getName());

        for (Role role : user.getRoles()) {
            userDTO.setRole(role.getName());
        }

        return userDTO;
    }

    public User convertToEntity(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        Type status = typeService.findByNameAndGroup(userDTO.getStatus(), "user status");
        user.setStatus(status);
        setUserRoles(userDTO, user);

        return user;
    }

    private void setUserRoles(UserDTO userDTO, User user) {
        Optional<Role> role = roleService.findByName(userDTO.getRole());
        Collection<Role> roles = new HashSet<>();
        role.ifPresent(roles::add);
        user.setRoles(roles);
    }
}
