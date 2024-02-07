package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Technology;
import com.jogger.rs.learning.materials.entities.TechnologyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TechnologyService {

    private TechnologyRepository repository;

    @Autowired
    public TechnologyService(TechnologyRepository repository) {
        this.repository = repository;
    }

    public Optional<Technology> findByName(TechnologyName name) {
        return repository.findByName(name);
    }
}
