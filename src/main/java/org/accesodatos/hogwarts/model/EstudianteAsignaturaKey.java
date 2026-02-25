package org.accesodatos.hogwarts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EstudianteAsignaturaKey implements Serializable {

    @Column(name = "id_estudiante")
    private int idEstudiante;

    @Column(name = "id_asignatura")
    private int idAsignatura;
}

