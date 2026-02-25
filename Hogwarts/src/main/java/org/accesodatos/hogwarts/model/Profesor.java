package org.accesodatos.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "profesor")
@Getter
@Setter
public class Profesor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profesor")
    private Long idProfesor;

    private String nombre;
    private String apellido;
    private LocalDate fechaInicio;

    @OneToOne
    @JoinColumn(name = "id_asignatura")
    @JsonIgnoreProperties("profesor")
    private Asignatura asignatura;

    @OneToOne(mappedBy = "jefeDeCasa")
    @JsonIgnore
    private Casa casaQueDirige;
}