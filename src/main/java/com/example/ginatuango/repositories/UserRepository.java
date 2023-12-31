package com.example.ginatuango.repositories;

import com.example.ginatuango.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    @Query(value = "SELECT u FROM " +
            "User u WHERE user_name =:userParam ")
    Optional<User> getUserByUsername(@Param("userParam") String username);
}
