package org.accesodatos.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "mascota")
@Getter
@Setter
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Long idMascota;

    private String nombre;
    private String especie;

    @OneToOne
    @JoinColumn(name = "id_estudiante")
    @JsonIgnoreProperties("mascota")
    private Estudiante estudiante;
}