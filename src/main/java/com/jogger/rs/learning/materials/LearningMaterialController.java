package com.jogger.rs.learning.materials;

import com.jogger.rs.auth.AuthManager;
import com.jogger.rs.dto.NewLMDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.labels.SuccessMessage;
import com.jogger.rs.learning.materials.entities.*;
import com.jogger.rs.utils.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LearningMaterialController {
    /**
     * Servis koji je zaduzen za rad sa materijalima za ucenje.
     */
    private LearningMaterialServiceInterface learningMaterialService;
    /**
     * Servis koji je zaduzen za autorizaciju korisnickih zahteva.
     */
    private AuthManager authManager;
    /**
     * Servis koji je zaduzen za kreiranje odgovora na korisnicke zahteve.
     */
    private ResponseFactory responseFactory;

    /**
     * Javni konstruktor.
     *
     * @param learningMaterialService servis koji je zaduzen za rad sa materijalima za ucenje.
     * @param authManager servis koji je zaduzen za autorizaciju korisnickih zahteva.
     * @param responseFactory servis koji je zaduzen za kreiranje odgovora na korisnicke zahteve.
     */
    @Autowired
    public LearningMaterialController(LearningMaterialServiceInterface learningMaterialService ,AuthManager authManager, ResponseFactory responseFactory) {
        this.learningMaterialService = learningMaterialService;
        this.authManager = authManager;
        this.responseFactory = responseFactory;
    }

    /**
     * Metoda koja je zaduzena za pronalazenje svih materijala za ucenje.
     *
     * @param request zahtev koji salje klijentska strana.
     * @param area oblast materijala za ucenje.
     * @param contentType tip sadrzaja materijala za ucenje.
     * @param level nivo materijala za ucenje.
     * @param platform platforma materijala za ucenje.
     * @param technology tehnologija materijala za ucenje.
     * @return ResponseEntity<Object> koji se puni podacima u zavisnosti da li je uspesno pronadjen materijal za ucenje.
     * @throws AuthenticationException ako korisnik nema pravo da pristupi datoj putanji.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> findAll(HttpServletRequest request,
                                           @RequestParam(name = "area", required = false) AreaName area,
                                           @RequestParam(name = "contentType", required = false) ContentTypeName contentType,
                                           @RequestParam(name = "level", required = false) LevelName level,
                                           @RequestParam(name = "platform", required = false) PlatformName platform,
                                           @RequestParam(name = "technology", required = false) TechnologyName technology) throws AuthenticationException {
       if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        return responseFactory.ok(learningMaterialService.findLearningMaterials(area, contentType, level, platform, technology));
    }

    /**
     * Metoda koja je zaduzena za brisanje materijala za ucenje.
     *
     * @param request zahtev koji salje klijentska strana.
     * @param id id materijala za ucenje.
     * @return ResponseEntity<Object> koji se puni podacima u zavisnosti da li je uspesno obrisan materijal za ucenje.
     * @throws AuthenticationException ako korisnik nema pravo da pristupi datoj putanji.
     */
    @DeleteMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> delete(HttpServletRequest request, @PathVariable(name = "id") Integer id) throws AuthenticationException {
        if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        learningMaterialService.deleteById(id);
        return responseFactory.ok(SuccessMessage.LM_DELETE_SUCCESS);
    }

    /**
     * Metoda koja je zaduzena za cuvanje materijala za ucenje.
     *
     * @param request zahtev koji salje klijentska strana.
     * @param newLMDto novi materijal za ucenje.
     * @return ResponseEntity<Object> koji se puni podacima u zavisnosti da li je uspesno sacuvan materijal za ucenje.
     * @throws AuthenticationException ako korisnik nema pravo da pristupi datoj putanji.
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> save(HttpServletRequest request, @RequestBody NewLMDto newLMDto) throws AuthenticationException {
        if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        learningMaterialService.save(newLMDto, request.getHeader("Token"));
        return responseFactory.ok(SuccessMessage.LM_SAVE_SUCCESS);
    }


}
