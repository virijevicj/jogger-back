package com.jogger.rs.learning.materials;

import com.jogger.rs.dto.NewLMDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.learning.materials.entities.*;
import com.jogger.rs.learning.materials.entities.service.*;
import com.jogger.rs.utils.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Implementacija servisa koji je zaduzen za rad sa materijalima za ucenje.
 *
 * @author Jovan Virijevic
 */
@Service
@RequiredArgsConstructor
public class LearningMaterialService implements LearningMaterialServiceInterface{

    /**
     * Repozitorijum za rad sa materijala sa ucenjem.
     */
    private final LearningMaterialRepository learningMaterialRepository;

    /**
     * Validator.
     */
    private final Validator validator;

    /**
     * Servis za rad sa oblastima materijala za ucenje.
     */
    private final AreaService areaService;

    /**
     * Servis za rad sa platformama materijala za ucenje.
     */
    private final PlatformService platformService;

    /**
     * Servis za rad sa tehnologijama materijala za ucenje.
     */
    private final TechnologyService technologyService;

    /**
     * Servis za rad sa tipom sadrzaja materijala za ucenje.
     */
    private final ContentTypeService contentTypeService;

    /**
     * Servis za rad sa nivoom materijala za ucenje.
     */
    private final LevelService levelService;

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

    @Override
    public void save(NewLMDto newLMDto) {
        String errorMessage = validator.validateNewLM(newLMDto);
        if (StringUtils.hasText(errorMessage))
            throw new IllegalArgumentException(errorMessage);

        Area area = areaService.findByName(newLMDto.getArea()).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NO_AREA_WITH_NAME + newLMDto.getArea())
        );
        Platform platform = platformService.findByName(newLMDto.getPlatform()).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NO_PLATFORM_WITH_NAME + newLMDto.getPlatform())
        );
        Technology technology = technologyService.findByName(newLMDto.getTechnology()).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NO_TECHNOLOGY_WITH_NAME + newLMDto.getTechnology())
        );
        Level level = levelService.findByName(newLMDto.getLevel()).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NO_LEVEL_WITH_NAME + newLMDto.getLevel())
        );
        ContentType contentType = contentTypeService.findByName(newLMDto.getContentType()).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NO_CONTENT_TYPE_WITH_NAME + newLMDto.getContentType())
        );

        LearningMaterial lm = new LearningMaterial();
        lm.setDescription(newLMDto.getDescription());
        lm.setLink(newLMDto.getLink());
        lm.setArea(area);
        lm.setContentType(contentType);
        lm.setTechnology(technology);
        lm.setLevel(level);
        lm.setPlatform(platform);
        lm.setActive(true);
        learningMaterialRepository.save(lm);
    }

}
