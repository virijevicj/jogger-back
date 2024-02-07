package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.Platform;
import com.jogger.rs.learning.materials.entities.PlatformName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlatformService {

    private PlatformRepository repository;

    @Autowired
    public PlatformService(PlatformRepository repository) {
        this.repository = repository;
    }

    public Optional<Platform> findByName(PlatformName name) {
        return repository.findByName(name);
    }
}
