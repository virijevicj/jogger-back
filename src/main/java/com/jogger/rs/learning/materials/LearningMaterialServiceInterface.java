package com.jogger.rs.learning.materials;

import com.jogger.rs.learning.materials.entities.*;

import java.util.List;

public interface LearningMaterialServiceInterface {

    List<LearningMaterial> findLearningMaterials(AreaName area, ContentTypeName contentType,
                                                 LevelName level, PlatformName platform, TechnologyName technology);
}
