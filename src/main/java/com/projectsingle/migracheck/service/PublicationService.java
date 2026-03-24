package com.projectsingle.migracheck.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import com.projectsingle.migracheck.entity.Procedure;
import com.projectsingle.migracheck.entity.Publication;

public interface  PublicationService {
    
    Publication createPublication(Publication publication, int userId);

    Publication updatePublication(int id, Publication publication, int userId);

    void deletePublication(int id, int userId);

    Page<Publication> getAllPublications(int page);

    Page<Publication> getAllPublicationsByUser(int page, int userId);

    Page<Publication> filterByProcedure(int page, Procedure procedure);

    Page<Publication> filterByDateCreation(LocalDateTime startDate, LocalDateTime endDate, int page);
}
