package com.jogger.rs.learning.materials;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Integer> {

    List<LearningMaterial> findAllByActiveTrue();
}
