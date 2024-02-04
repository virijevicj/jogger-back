package com.jogger.rs.dto;

import com.jogger.rs.learning.materials.entities.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class LMEntitiesDto {
    List<AreaName> areas;
    List<ContentTypeName> contentTypes;
    List<LevelName> levels;
    List<PlatformName> platforms;
    List<TechnologyName> technologies;
}
