package com.skillnest.arka.infrastructure.config;

import com.skillnest.arka.application.service.UserService;
import com.skillnest.arka.domain.port.in.CreateUserUseCase;
import com.skillnest.arka.domain.port.in.DeleteUserUseCase;
import com.skillnest.arka.domain.port.in.GetUserUseCase;
import com.skillnest.arka.domain.port.in.UpdateUserUseCase;
import com.skillnest.arka.domain.port.out.DirectionRepository;
import com.skillnest.arka.domain.port.out.RoleRepository;
import com.skillnest.arka.domain.port.out.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainConfiguration {
    
    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository, 
                                              RoleRepository roleRepository,
                                              DirectionRepository directionRepository) {
        return new UserService(userRepository, roleRepository, directionRepository);
    }
    
    @Bean
    public GetUserUseCase getUserUseCase(UserRepository userRepository, 
                                        RoleRepository roleRepository,
                                        DirectionRepository directionRepository) {
        return new UserService(userRepository, roleRepository, directionRepository);
    }
    
    @Bean
    public UpdateUserUseCase updateUserUseCase(UserRepository userRepository, 
                                              RoleRepository roleRepository,
                                              DirectionRepository directionRepository) {
        return new UserService(userRepository, roleRepository, directionRepository);
    }
    
    @Bean
    public DeleteUserUseCase deleteUserUseCase(UserRepository userRepository, 
                                              RoleRepository roleRepository,
                                              DirectionRepository directionRepository) {
        return new UserService(userRepository, roleRepository, directionRepository);
    }
}
