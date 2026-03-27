package com.projectsingle.migracheck.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.projectsingle.migracheck.entity.Procedure;
import com.projectsingle.migracheck.entity.Publication;
import com.projectsingle.migracheck.entity.User;
import com.projectsingle.migracheck.repository.PublicationRepository;


@Service
public class PublicationServiceImpl implements PublicationService {
    
    private final PublicationRepository publicationRepository;
    private final UserService userService;

    public PublicationServiceImpl(PublicationRepository publicationRepository, UserService userService){
        this.publicationRepository = publicationRepository;
        this.userService = userService;
    }

    private Pageable getPageable(int page) {
        return PageRequest.of(page, 10, Sort.by("dateCreation").descending());
    }

    @Override
    public Publication createPublication(Publication publicationNew, int userId){
        User user = userService.getUserById(userId);
        publicationNew.setUser(user);
        publicationNew.setDateCreation(LocalDateTime.now());
        //publicationNew.setDateUpdate(null);
        return publicationRepository.save(publicationNew);
    }

    @Override
    public Publication updatePublication(int id, Publication publication, int userId) {

        Publication existingPublication = publicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

            if (!existingPublication.getUser().getId().equals(userId)) {
                throw new RuntimeException("No tienes permiso para editar esta publicación");
        }

        existingPublication.setMessage(publication.getMessage());
        existingPublication.setProcedure(publication.getProcedure());
        existingPublication.setDateUpdate(LocalDateTime.now());
        return publicationRepository.save(existingPublication);
    }

    @Override
    public void deletePublication(int id, int userId) {
        Publication existingPublication = publicationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));
        if (!existingPublication.getUser().getId().equals(userId)) {
            throw new RuntimeException("No tienes permiso para eliminar esta publicación");
        }
        publicationRepository.delete(existingPublication);
    }

    @Override
    public Page<Publication> getAllPublications(int page) {
        return publicationRepository.findAll(getPageable(page));
    }

    @Override
    public Page<Publication> getAllPublicationsByUser(int page, int userId) {
        return publicationRepository.findByUserId(userId, getPageable(page));
    }

    @Override
    public Page<Publication> filterByProcedure(int page, Procedure procedure) {
        return publicationRepository.findByProcedure(procedure, getPageable(page));
    }

    @Override
    public Page<Publication> filterByDateCreation(LocalDateTime startDate, LocalDateTime endDate, int page) {
        return publicationRepository.findByDateCreation(startDate, endDate, getPageable(page));
    }
}
