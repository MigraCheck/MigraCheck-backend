package com.projectsingle.migracheck.entity;

import java.time.LocalDateTime;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
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
    private LocalDateTime dateCreation;

    @CreationTimestamp
    @Column(name = "data_update", updatable = false, nullable = false)
    private LocalDateTime dateUpdate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Enumerated
    @Column(nullable = false)
    private Procedure procedure;
}


