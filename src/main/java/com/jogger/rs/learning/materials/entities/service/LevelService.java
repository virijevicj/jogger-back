package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Level;
import com.jogger.rs.learning.materials.entities.LevelName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LevelService {

    private LevelRepository repository;

    @Autowired
    public LevelService(LevelRepository repository) {
        this.repository = repository;
    }

    public Optional<Level> findByName(LevelName name) {
        return repository.findByName(name);
    }
}
