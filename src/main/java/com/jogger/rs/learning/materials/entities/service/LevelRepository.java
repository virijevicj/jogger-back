package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Level;
import com.jogger.rs.learning.materials.entities.LevelName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {

    Optional<Level> findByName(LevelName name);
}
