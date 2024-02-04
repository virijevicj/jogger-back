package com.jogger.rs.learning.materials;

import com.jogger.rs.learning.materials.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Integer> {

    List<LearningMaterial> findAllByActiveTrue();

    @Query("""
            select o from LearningMaterial o
            where o.active = true
            and (:area is null or o.area.name = :area)
            and (:contentType is null or o.contentType.name = :contentType)
            and (:level is null or o.level.name = :level)
            and (:platform is null or o.platform.name = :platform)
            and (:technology is null or o.technology.name = :technology)
            """)
    List<LearningMaterial> findAllByEntitiesParameters(AreaName area, ContentTypeName contentType,
                                                       LevelName level, PlatformName platform, TechnologyName technology);
}
