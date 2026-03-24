package com.projectsingle.migracheck.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.projectsingle.migracheck.entity.Publication;
import com.projectsingle.migracheck.entity.Procedure;

public interface PublicationRepository extends JpaRepository<Publication, Integer>{

    public Page<Publication> findByUserId(int userId, Pageable pageable);

    public Page<Publication> findByProcedure(Procedure procedure, Pageable pageable);

    public Page<Publication> findByDateCreation(LocalDateTime start, LocalDateTime end, Pageable pageable);
}
