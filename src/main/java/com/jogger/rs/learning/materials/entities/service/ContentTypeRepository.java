package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.ContentType;
import com.jogger.rs.learning.materials.entities.ContentTypeName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContentTypeRepository extends JpaRepository<ContentType, Integer> {

    Optional<ContentType> findByName(ContentTypeName name);
}
