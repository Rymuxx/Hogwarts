package org.accesodatos.hogwarts.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "estudiante")
@Getter
@Setter
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private int id;

    private String nombre;
    private String apellido;

    @ManyToOne
    @JoinColumn(name = "id_casa")
    private Casa casa;

    @Column(name = "anyo_curso")
    private int anyoCurso;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL, orphanRemoval = true)
    private Mascota mascota;

    @OneToMany(mappedBy = "estudiante")
    private List<EstudianteAsignatura> asignaturas;
}
