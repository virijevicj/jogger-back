package com.jogger.rs.learning.materials;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LearningMaterialService implements LearningMaterialServiceInterface{

    private LearningMaterialRepository learningMaterialRepository;

    public LearningMaterialService(LearningMaterialRepository learningMaterialRepository) {
        this.learningMaterialRepository = learningMaterialRepository;
    }

    @Autowired


    @Override
    public List<LearningMaterial> findAll() {
        return learningMaterialRepository.findAllByActiveTrue();
    }
}
