package com.skillnest.arka.infrastructure.adapter.out.persistence;

import com.skillnest.arka.domain.model.User;
import com.skillnest.arka.domain.port.out.UserRepository;
import com.skillnest.arka.infrastructure.adapter.out.persistence.entity.UserJpaEntity;
import com.skillnest.arka.infrastructure.adapter.out.persistence.mapper.UserPersistenceMapper;
import com.skillnest.arka.infrastructure.adapter.out.persistence.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserRepository {
    
    private final UserJpaRepository userJpaRepository;
    private final UserPersistenceMapper userPersistenceMapper;
    
    @Override
    public User save(User user) {
        UserJpaEntity entity = userPersistenceMapper.toEntity(user);
        UserJpaEntity savedEntity = userJpaRepository.save(entity);
        return userPersistenceMapper.toDomain(savedEntity);
    }
    
    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id)
                .map(userPersistenceMapper::toDomain);
    }
    
    @Override
    public List<User> findAll() {
        return userJpaRepository.findAll()
                .stream()
                .map(userPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        return userJpaRepository.existsById(id);
    }
}
