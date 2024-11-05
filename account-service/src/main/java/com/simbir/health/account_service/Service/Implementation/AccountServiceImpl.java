package com.simbir.health.account_service.Service.Implementation;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.simbir.health.account_service.Class.Role;
import com.simbir.health.account_service.Class.User;
import com.simbir.health.account_service.Class.DTO.AdminCreateUpdateUserDTO;
import com.simbir.health.account_service.Class.DTO.UserReadDTO;
import com.simbir.health.account_service.Class.DTO.UserUpdateDTO;
import com.simbir.health.account_service.Repository.RoleRepository;
import com.simbir.health.account_service.Repository.UserRepository;
import com.simbir.health.account_service.Service.Interface.AccountService;
import com.simbir.health.account_service.Util.JwtTokenUtils;
import com.simbir.health.account_service.Util.Mapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final JwtTokenUtils jwtTokenUtils;

    private final Mapper mapper;

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public UserReadDTO getUserInformation(String token) {
        User user = userRepository.findByUsername(jwtTokenUtils.getUsernameFromToken(token))
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapper.userToUserReadDTO(user);
    }

    @Override
    @Transactional
    public void updateUser(UserUpdateDTO userUpdateDTO, String token) {
        User user = userRepository.findByUsername(jwtTokenUtils.getUsernameFromToken(token))
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(userUpdateDTO.getFirstName());
        user.setLastName(userUpdateDTO.getLastName());
        user.setPassword(userUpdateDTO.getPassword());
        userRepository.save(user);

    }

    @Override
    @Transactional
    public Page<UserReadDTO> getAllUsers(Integer page, Integer size) {
        PageRequest pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable).map(mapper::userToUserReadDTO);
    }

    @Override
    @Transactional
    public void createUserByAdmin(AdminCreateUpdateUserDTO user) {
        List<Role> role = roleRepository.findAllByNameIn(user.getRoles().stream().map(Role::getName).toList());
        User newUser = User.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(role)
                .build();
        userRepository.save(newUser);
    }

    @Override
    @Transactional
    public void updateUserByAdmin(AdminCreateUpdateUserDTO user) {
        List<Role> role = roleRepository.findAllByNameIn(user.getRoles().stream().map(Role::getName).toList());
        User existingUser = userRepository.findByUsername(user.getUsername()).get();
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setPassword(user.getPassword());
        existingUser.setRoles(role);
        existingUser.setUsername(user.getUsername());
        userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public void deleteUserByAdmin(Long id) {
        userRepository.deleteById(id);
    }

}
