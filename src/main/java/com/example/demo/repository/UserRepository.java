package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{

    @Query(value = "select u from User u where u.phone=?1")
    Optional<User> findByPhone(String phone);

    @Modifying
    @Transactional
    @Query(value = "delete from User where phone=?1")
    void deleteByPhone(String phone);

    @Modifying
    @Transactional
    @Query(value = "update User u set u.email =?1, u.username =?2 " +
            "where u.phone =?3 and ?1 is not null and ?1 <> ''" +
            "and ?2 is not null and ?2 <> '' ")
    void updateByPhone(String email, String username, String phone);

}
