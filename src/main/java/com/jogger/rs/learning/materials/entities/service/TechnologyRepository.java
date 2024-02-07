package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Technology;
import com.jogger.rs.learning.materials.entities.TechnologyName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnologyRepository extends JpaRepository<Technology, Integer> {

    Optional<Technology> findByName(TechnologyName name);
}
