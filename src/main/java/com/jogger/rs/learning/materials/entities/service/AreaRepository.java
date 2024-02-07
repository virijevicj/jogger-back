package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Area;
import com.jogger.rs.learning.materials.entities.AreaName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {

    Optional<Area> findByName(AreaName name);
}
