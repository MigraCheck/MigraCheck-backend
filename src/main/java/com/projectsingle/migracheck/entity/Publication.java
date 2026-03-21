package com.projectsingle.migracheck.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "publications")
@Data
@NoArgsConstructor
public class Publication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "La publicación no puede estar vacía.")
    @Size(min = 10, max = 300, message = "La descripción debe tener entre 10 y 300 caracteres.")
    private String message;

    @CreationTimestamp
    @Column(name = "data_creation", updatable = false, nullable = false)
    private LocalDateTime dataCreation;

    @Enumerated
    private Procedure procedure;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "id")
    private User creatorId;
}
