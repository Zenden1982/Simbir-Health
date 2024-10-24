package com.simbir.health.account_service.Service.Implementation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.simbir.health.account_service.Class.User;
import com.simbir.health.account_service.Class.DTO.UserReadDTO;
import com.simbir.health.account_service.Repository.UserRepository;
import com.simbir.health.account_service.Repository.UserSpecification;
import com.simbir.health.account_service.Service.Interface.DoctorService;
import com.simbir.health.account_service.Util.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final UserRepository userRepository;

    private final Mapper mapper;

    @Override
    public UserReadDTO getDoctorById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
        return mapper.userToUserReadDTO(user);
    }

    @Override
    public Page<UserReadDTO> getAllDoctors(String nameFilter, Integer page, Integer size) {
        Specification<User> spec = Specification.where(null);
        if (nameFilter != null) {
            String[] names = nameFilter.split(" ");
            spec = spec.and(UserSpecification.firstName(names[0]));
            if (names.length > 1) {
                spec = spec.and(UserSpecification.lastName(names[1]));
            }
        }
        PageRequest pageable = PageRequest.of(page, size);
        return userRepository.findAll(spec, pageable).map(mapper::userToUserReadDTO);
    }

}
