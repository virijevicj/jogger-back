package com.jogger.rs.learning.materials.entities.service;

import com.jogger.rs.learning.materials.entities.ContentType;
import com.jogger.rs.learning.materials.entities.ContentTypeName;
import com.jogger.rs.learning.materials.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContentTypeService {

    private ContentTypeRepository repository;

    @Autowired
    public ContentTypeService(ContentTypeRepository repository) {
        this.repository = repository;
    }

    public Optional<ContentType> findByName(ContentTypeName name) {
        return repository.findByName(name);
    }
}
