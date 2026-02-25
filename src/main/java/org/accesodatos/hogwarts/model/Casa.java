package org.accesodatos.hogwarts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Table(name = "casa")
@Getter
@Setter
public class Casa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_casa")
    private Long idCasa;

    private String nombre;
    private String fundador;
    private String fantasma;

    @OneToMany(mappedBy = "casa")
    @JsonIgnore
    private List<Estudiante> estudiantes;

    @OneToOne
    @JoinColumn(name = "id_jefe")
    private Profesor jefeDeCasa;
}