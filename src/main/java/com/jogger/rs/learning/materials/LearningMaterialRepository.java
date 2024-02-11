package com.jogger.rs.learning.materials;

import com.jogger.rs.learning.materials.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repozitorijum za rad sa materijalima za ucenje.
 *
 * @author Jovan Virijevic
 */
@Repository
public interface LearningMaterialRepository extends JpaRepository<LearningMaterial, Integer> {

    /**
     * Metoda koja pronalazi sve aktivne materijale za ucenje (materijale koji nisu obrisani)
     *
     * @return listu aktivnih materijala za ucenje
     */
    List<LearningMaterial> findAllByActiveTrue();

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
    @Query("""
            select o from LearningMaterial o
            where o.active = true
            and (:area is null or o.area.name = :area)
            and (:contentType is null or o.contentType.name = :contentType)
            and (:level is null or o.level.name = :level)
            and (:platform is null or o.platform.name = :platform)
            and (:technology is null or o.technology.name = :technology)
            """)
    List<LearningMaterial> findAllByEntitiesParameters(AreaName area, ContentTypeName contentType,
                                                       LevelName level, PlatformName platform, TechnologyName technology);
}
