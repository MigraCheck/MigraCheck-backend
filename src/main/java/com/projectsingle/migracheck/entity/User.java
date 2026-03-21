package com.projectsingle.migracheck.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre no puede estar vacío.")
    @Size(min = 2, max = 30, message = "El nombre debe tener entre 2 y 30 caracteres.")
    private String name;

    @NotBlank(message = "El apellido no puede estar vacío.")
    @Size(min = 2, max = 30, message = "El apellido debe tener entre 2 y 30 caracteres.")
    private String lastName;

    @NotBlank(message = "El nombre de usuario no puede estar vacío.")
    @Size(min = 3, max = 30, message = "El nombre de usuario debe tener entre 3 y 30 caracteres.")
    @Column(unique = true, nullable = false, length = 30)
    private String userName;

    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Size(min = 6, max = 10, message = "La contraseña debe tener entre 6 y 10 caracteres.")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "El correo electrónico no puede estar vacío.")
    @Column(unique = true, nullable = false)
    private String email;
}
