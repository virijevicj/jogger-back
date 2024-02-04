package com.jogger.rs.learning.materials;

import com.jogger.rs.learning.materials.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LearningMaterialService implements LearningMaterialServiceInterface{

    private LearningMaterialRepository learningMaterialRepository;

    @Autowired
    public LearningMaterialService(LearningMaterialRepository learningMaterialRepository) {
        this.learningMaterialRepository = learningMaterialRepository;
    }

    @Override
    public List<LearningMaterial> findLearningMaterials(AreaName area, ContentTypeName contentType,
                                                        LevelName level, PlatformName platform, TechnologyName technology) {
        if (area != null || contentType != null || level != null || platform != null || technology != null)
            return learningMaterialRepository.findAllByEntitiesParameters(area, contentType, level, platform, technology);
        return learningMaterialRepository.findAllByActiveTrue();
    }
}