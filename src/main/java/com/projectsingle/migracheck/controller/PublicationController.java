package com.projectsingle.migracheck.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectsingle.migracheck.entity.Procedure;
import com.projectsingle.migracheck.entity.Publication;
import com.projectsingle.migracheck.service.PublicationService;

@RestController
@RequestMapping("/api/v1/publications")
public class PublicationController {

    private final PublicationService publicationService;

    public PublicationController(PublicationService publicationService){
        this.publicationService = publicationService;
    }

    @PostMapping("{userId}")
    public ResponseEntity<Publication> createPublication(
            @RequestBody Publication publication,
            @PathVariable int userId) {
        Publication newPublication = publicationService.createPublication(publication, userId);
        return new ResponseEntity<>(newPublication, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publication> updatePublication(
            @PathVariable int id,
            @RequestParam int userId,
            @RequestBody Publication publication) {

        try {
            Publication updated = publicationService.updatePublication(id, publication, userId);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePublication(
            @PathVariable int id,
            @RequestParam int userId) {

            publicationService.deletePublication(id, userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Page<Publication>> getAllPublications(
            @RequestParam(defaultValue = "0") int page) {
        Page<Publication> publications = publicationService.getAllPublications(page);
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<Publication>> getPublicationsByUser(
            @RequestParam(defaultValue = "0") int page,
            @PathVariable int userId) {
        Page<Publication> publications = publicationService.getAllPublicationsByUser(page, userId);
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    @GetMapping("/filter/procedure")
    public ResponseEntity<Page<Publication>> filterByProcedure(
            @RequestParam Procedure procedure,
            @RequestParam(defaultValue = "0") int page) {

        return ResponseEntity.ok(
                publicationService.filterByProcedure(page, procedure)
        );
    }

    @GetMapping("/filter/date")
    public ResponseEntity<Page<Publication>> filterByDateCreation(
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam(defaultValue = "0") int page) {

        LocalDateTime start = LocalDateTime.parse(startDate);
        LocalDateTime end = LocalDateTime.parse(endDate);

        return ResponseEntity.ok(
                publicationService.filterByDateCreation(start, end, page)
        );
    }
}
