package com.jogger.rs.learning.materials;

import com.jogger.rs.dto.NewLMDto;
import com.jogger.rs.learning.materials.entities.*;

import java.util.List;
import java.util.Optional;

/**
 * Servis koji je zaduzen za rad sa materijalima za ucenje.
 *
 * @author Jovan Virijevic
 */

public interface LearningMaterialServiceInterface {

    /**
     * Metoda koja pronalazi materijal za ucenje na osnovu id vrednosti.
     *
     * @param id jedinstvena vrednost materijala za ucenje
     * @return LearningMaterial
     */
    Optional<LearningMaterial> findById(Integer id);

    /**
     * Metoda koja vrsi pretragu materijala za ucenje na osnovu kriterijuma pretrage.
     *
     * @param area oblast materijala za ucenje
     * @param contentType tip sadrzaja materijal za ucenje
     * @param level nivo materijala za ucenje
     * @param platform platforma materijala za ucenje
     * @param technology tehnologija materijala za ucenje
     * @return lista materijala za ucenje
     */
    List<LearningMaterial> findLearningMaterials(AreaName area, ContentTypeName contentType,
                                                 LevelName level, PlatformName platform, TechnologyName technology);

    /**
     * Metoda koja brise materijal za ucenje.
     *
     * @param id jedinstvena vrednost materijala za ucenje
     */
    void deleteById(Integer id);

    /**
     * Metoda koja dodaje novi materijal za ucenje.
     *
     * @param newLMDto novi materijal za ucenje
     * @param token jedinstveni identifikator korisnika
     */
    void save(NewLMDto newLMDto, String token);
}
