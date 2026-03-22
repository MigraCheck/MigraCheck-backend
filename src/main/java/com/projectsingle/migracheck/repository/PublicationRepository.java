package com.projectsingle.migracheck.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectsingle.migracheck.entity.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Integer>{
    
}
