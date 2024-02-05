package com.jogger.rs.learning.materials;

import com.jogger.rs.learning.materials.entities.*;

import java.util.List;
import java.util.Optional;

public interface LearningMaterialServiceInterface {

    Optional<LearningMaterial> findById(Integer id);

    List<LearningMaterial> findLearningMaterials(AreaName area, ContentTypeName contentType,
                                                 LevelName level, PlatformName platform, TechnologyName technology);

    void deleteById(Integer id);
}
