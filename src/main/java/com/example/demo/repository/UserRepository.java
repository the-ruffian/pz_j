package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{

    @Query(value = "select u from User u where u.phone=?1")
    Optional<User> findByPhone(String phone);


}
