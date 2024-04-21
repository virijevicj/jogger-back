package com.jogger.rs.learning.materials;

import com.jogger.rs.dto.NewLMDto;
import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.labels.SuccessMessage;
import com.jogger.rs.learning.materials.entities.*;
import com.jogger.rs.utils.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;

/**
 * Kontroler koji je zaduzen za rad sa materijalima za ucenje, odnosno pronalazenje materijala za ucenje.
 *
 * @author Jovan Virijevic
 */
@RestController
@RequestMapping(RequestMappingPrefix.LEARNING_MATERIAL)
@RequiredArgsConstructor
public class LearningMaterialController {

    /**
     * Servis koji je zaduzen za rad sa materijalima za ucenje.
     */
    private final LearningMaterialServiceInterface learningMaterialService;

    /**
     * Metoda koja je zaduzena za pronalazenje svih materijala za ucenje.
     *
     * @param area oblast materijala za ucenje.
     * @param contentType tip sadrzaja materijala za ucenje.
     * @param level nivo materijala za ucenje.
     * @param platform platforma materijala za ucenje.
     * @param technology tehnologija materijala za ucenje.
     * @return StandardResponseDto
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> findAll(@RequestParam(name = "area", required = false) AreaName area,
                                           @RequestParam(name = "contentType", required = false) ContentTypeName contentType,
                                           @RequestParam(name = "level", required = false) LevelName level,
                                           @RequestParam(name = "platform", required = false) PlatformName platform,
                                           @RequestParam(name = "technology", required = false) TechnologyName technology) {

        return ResponseFactory.ok(learningMaterialService.findLearningMaterials(area, contentType, level, platform, technology));
    }

    /**
     * Metoda koja je zaduzena za brisanje materijala za ucenje.
     *
     * @param id id materijala za ucenje.
     * @return StandardResponseDto
     */
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> delete(@PathVariable(name = "id") Integer id) {
        learningMaterialService.deleteById(id);
        return ResponseFactory.ok(SuccessMessage.LM_DELETE_SUCCESS);
    }

    /**
     * Metoda koja je zaduzena za cuvanje materijala za ucenje.
     *
     * @param newLMDto novi materijal za ucenje.
     * @return StandardResponseDto
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> save(@Valid @RequestBody NewLMDto newLMDto) {
        learningMaterialService.save(newLMDto);
        return ResponseFactory.ok(SuccessMessage.LM_SAVE_SUCCESS);
    }

}
