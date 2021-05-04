package com.example.demo.repository;

import com.example.demo.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<Permission,Integer> {

    @Query(value = "select p from Permission p where p.permission_name=?1")
    Optional<Permission> findByPermissionName(String permission_name);
}
