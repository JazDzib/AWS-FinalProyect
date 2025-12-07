package com.awsproyect.AWS_Proyect.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 100)
    private String nombres;
    @Column(nullable = false, length = 100)
    private String apellidos;
    @Column(nullable = false, length = 50)
    private String matricula;
    @Column(nullable = false)
    private Double promedio;
    @Column(nullable = false, length = 150)
    private String fotoPerfilUrl;
    @Column(nullable = false, length = 50)
    private String password;
}
