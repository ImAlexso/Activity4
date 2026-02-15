package org.example.activity2unit4.repository;

import org.example.activity2unit4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
