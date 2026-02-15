package org.example.activity2unit4.repository;

import org.example.activity2unit4.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Integer> {
    List<Game> findByUserIdOrderByIdAsc(Integer id);

}
