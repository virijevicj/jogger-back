package com.jogger.rs.learning.materials;

import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.learning.materials.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LearningMaterialService implements LearningMaterialServiceInterface{

    private LearningMaterialRepository learningMaterialRepository;

    @Autowired
    public LearningMaterialService(LearningMaterialRepository learningMaterialRepository) {
        this.learningMaterialRepository = learningMaterialRepository;
    }

    @Override
    public Optional<LearningMaterial> findById(Integer id) {
        if (id == null) return Optional.empty();
        return learningMaterialRepository.findById(id);
    }

    @Override
    public List<LearningMaterial> findLearningMaterials(AreaName area, ContentTypeName contentType,
                                                        LevelName level, PlatformName platform, TechnologyName technology) {
        if (area != null || contentType != null || level != null || platform != null || technology != null)
            return learningMaterialRepository.findAllByEntitiesParameters(area, contentType, level, platform, technology);
        return learningMaterialRepository.findAllByActiveTrue();
    }

    @Override
    public void deleteById(Integer id) {
        LearningMaterial lm = findById(id).orElseThrow(() ->
                new NoSuchElementException(ErrorMessage.NO_LEARNING_MATERIAL_FOUND + id));
        lm.setActive(false);
        learningMaterialRepository.save(lm);
    }
}
