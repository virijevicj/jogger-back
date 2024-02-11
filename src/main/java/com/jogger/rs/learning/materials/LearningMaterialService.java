package com.jogger.rs.learning.materials;

import com.jogger.rs.auth.SessionManager;
import com.jogger.rs.dto.NewLMDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.learning.materials.entities.*;
import com.jogger.rs.learning.materials.entities.service.*;
import com.jogger.rs.user.User;
import com.jogger.rs.user.UserServiceInterface;
import com.jogger.rs.user.UserSession;
import com.jogger.rs.utils.Validator;
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
public class LearningMaterialService implements LearningMaterialServiceInterface{

    /**
     * Repozitorijum za rad sa materijala sa ucenjem.
     */
    private LearningMaterialRepository learningMaterialRepository;

    /**
     * Validator.
     */
    private Validator validator;

    /**
     * Servis za rad sa korisnickim sesijama.
     */
    private SessionManager sessionManager;

    /**
     * Servis za rad sa korisnicima.
     */
    private UserServiceInterface userService;

    /**
     * Servis za rad sa oblastima materijala za ucenje.
     */
    private AreaService areaService;

    /**
     * Servis za rad sa platformama materijala za ucenje.
     */
    private PlatformService platformService;

    /**
     * Servis za rad sa tehnologijama materijala za ucenje.
     */
    private TechnologyService technologyService;

    /**
     * Servis za rad sa tipom sadrzaja materijala za ucenje.
     */
    private ContentTypeService contentTypeService;

    /**
     * Servis za rad sa nivoom materijala za ucenje.
     */
    private LevelService levelService;

    /**
     * Jovni konstruktor.
     *
     * @param learningMaterialRepository repozitorijum za rad sa materijala sa ucenjem
     * @param validator validator
     * @param sessionManager servis za rad sa korisnickim sesijama
     * @param userService servis za rad sa korisnicima
     * @param areaService servis za rad sa oblastima materijala za ucenje
     * @param platformService servis za rad sa platformama materijala za ucenje
     * @param technologyService servis za rad sa tehnologijama materijala za ucenje
     * @param contentTypeService servis za rad sa tipom sadrzaja materijala za ucenje
     * @param levelService servis za rad sa nivoom materijala za ucenje
     */
    public LearningMaterialService(LearningMaterialRepository learningMaterialRepository, Validator validator, SessionManager sessionManager, UserServiceInterface userService, AreaService areaService,
                                   PlatformService platformService, TechnologyService technologyService, ContentTypeService contentTypeService, LevelService levelService) {
        this.learningMaterialRepository = learningMaterialRepository;
        this.validator = validator;
        this.sessionManager = sessionManager;
        this.userService = userService;
        this.areaService = areaService;
        this.platformService = platformService;
        this.technologyService = technologyService;
        this.contentTypeService = contentTypeService;
        this.levelService = levelService;
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

    @Override
    public void save(NewLMDto newLMDto, String token) {
        String errorMessage = validator.validateNewLM(newLMDto);
        if (StringUtils.hasText(errorMessage))
            throw new IllegalArgumentException(errorMessage);
        UserSession userSession = sessionManager.getUserFromSession(token).get();
        User user = userService.findById(userSession.getKeyUser()).orElseThrow(
                () -> new NoSuchElementException(ErrorMessage.NO_USER_FOUND_WITH_ID + userSession.getKeyUser())
        );
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
