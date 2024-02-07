package com.jogger.rs.dto;

import com.jogger.rs.learning.materials.entities.*;
import lombok.Data;

@Data
public class NewLMDto {
    private String description;
    private String link;
    private AreaName area;
    private ContentTypeName contentType;
    private LevelName level;
    private PlatformName platform;
    private TechnologyName technology;
}
