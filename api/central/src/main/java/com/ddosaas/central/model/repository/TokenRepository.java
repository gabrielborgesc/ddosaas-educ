package com.ddosaas.central.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ddosaas.central.model.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {

    boolean existsByToken(String token);
    
}
