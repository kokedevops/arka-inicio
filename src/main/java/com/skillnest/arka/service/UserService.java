package com.skillnest.arka.service;

import com.skillnest.arka.dto.UserCreateDTO;
import com.skillnest.arka.dto.UserDTO;
import com.skillnest.arka.mapper.DirectionMapper;
import com.skillnest.arka.mapper.RoleMapper;
import com.skillnest.arka.mapper.UserMapper;
import com.skillnest.arka.model.Direction;
import com.skillnest.arka.model.Role;
import com.skillnest.arka.model.User;
import com.skillnest.arka.repository.DirectionRepository;
import com.skillnest.arka.repository.RoleRepository;
import com.skillnest.arka.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final DirectionService directionService;

    public List<UserDTO> getUserViewUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.usersToUserDTOs(users);
    }

    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        return userMapper.userToUserDTO(user);
    }

    public User getEntityUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("user not found"));
    }

     public UserDTO createUser (UserCreateDTO newUser){
        User user = userMapper.userCreateDTOtoUser(newUser);
        if(newUser.getRole() != null && newUser.getRole().getId() != null){
            Role role = roleService.getRoleEntityById(newUser.getRole().getId());
            user.setRole(role);
        }
        if (newUser.getDirection() != null && newUser.getDirection().getId() != null){
            Direction direction = directionService.getDirectionEntityById(newUser.getDirection().getId());
            user.setDirection(direction);
        }

        // user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        User createUser = userRepository.save(user);
        return userMapper.userToUserDTO(createUser);
     }

     public UserDTO updateUser (Long id, UserCreateDTO userUpdateDTO){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User not foud"));
        userMapper.updateUserFromDTO(userUpdateDTO, user);
        if (userUpdateDTO.getRole() == null || userUpdateDTO.getRole().getId() == null){
            throw  new IllegalArgumentException("The role is mandatory to update the user");
        }
        Role role = roleService.getRoleEntityById(userUpdateDTO.getRole().getId());
        user.setRole(role);
        if (userUpdateDTO.getDirection() == null || userUpdateDTO.getDirection().getId() == null){
            throw  new IllegalArgumentException("The direction is mandatory to update the user");
        }
        Direction direction = directionService.getDirectionEntityById(userUpdateDTO.getDirection().getId());
        user.setDirection(direction);

        User userUpdated = userRepository.save(user);
        return userMapper.userToUserDTO(userUpdated);
     }

    public  boolean deleteUser (Long id){
        Optional<User> userExiste= userRepository.findById(id);
        if (userExiste.isPresent()){
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }


}
