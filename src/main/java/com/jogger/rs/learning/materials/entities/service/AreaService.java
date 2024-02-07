package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Area;
import com.jogger.rs.learning.materials.entities.AreaName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AreaService {

    private AreaRepository repository;

    @Autowired
    public AreaService(AreaRepository repository) {
        this.repository = repository;
    }

    public Optional<Area> findByName(AreaName name) {
        return repository.findByName(name);
    }
}
