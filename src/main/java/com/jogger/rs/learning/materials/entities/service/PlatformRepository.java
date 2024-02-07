package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Platform;
import com.jogger.rs.learning.materials.entities.PlatformName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Integer> {

    Optional<Platform> findByName(PlatformName name);
}
