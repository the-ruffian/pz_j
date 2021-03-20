package com.example.demo.repository;

import com.example.demo.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    @Query(value = "select t from Token t where t.phone=?1")
    Optional<Token> findByPhone(String phone);

    @Query(value = "select t from Token t where t.token=?1")
    Optional<Token> findByToken(String token);
}
