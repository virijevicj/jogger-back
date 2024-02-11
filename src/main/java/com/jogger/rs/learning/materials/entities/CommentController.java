package com.jogger.rs.learning.materials.entities;

import com.jogger.rs.auth.AuthManager;
import com.jogger.rs.dto.NewCommentDto;
import com.jogger.rs.labels.ErrorMessage;
import com.jogger.rs.labels.RequestMappingPrefix;
import com.jogger.rs.labels.SuccessMessage;
import com.jogger.rs.utils.ResponseFactory;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;

/**
 * Kontroler koji je zaduzen za rad sa komentarima, odnosno dodavanje novih komentara.
 *
 * @author Jovan Virijevic
 */
@RestController
@RequestMapping(RequestMappingPrefix.LEARNING_MATERIAL_COMMENT)
public class CommentController {
    /**
     * Servis koji je zaduzen za rad sa komentarima.
     */
    private CommentServiceInterface commentService;
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
     * @param commentService servis koji je zaduzen za rad sa komentarima.
     * @param authManager servis koji je zaduzen za autorizaciju korisnickih zahteva.
     * @param responseFactory servis koji je zaduzen za kreiranje odgovora na korisnicke zahteve.
     */
    @Autowired
    public CommentController(CommentServiceInterface commentService, AuthManager authManager, ResponseFactory responseFactory) {
        this.commentService = commentService;
        this.authManager = authManager;
        this.responseFactory = responseFactory;
    }

    /**
     * Metoda koja je zaduzena za dodavanje novog komentara.
     *
     * @param request zahtev koji salje klijentska strana.
     * @param commentDto novi komentar.
     * @return ResponseEntity<Object> koji se puni podacima u zavisnosti da li je uspesno dodat komentar.
     * @throws AuthenticationException ako korisnik nema pravo da pristupi datoj putanji.
     */
    @PostMapping(name = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> saveComment(HttpServletRequest request, @RequestBody NewCommentDto commentDto) throws AuthenticationException {
        if (!authManager.auth(request))
            return responseFactory.forbidden(ErrorMessage.ACCESS_FORBIDDEN + request.getRequestURI());
        commentService.save(commentDto, request.getHeader("Token"));
        return responseFactory.ok(SuccessMessage.COMMENT_SAVE_SUCCESS);
    }
}
