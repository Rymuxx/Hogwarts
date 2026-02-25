package org.accesodatos.hogwarts.dto.response;

import java.time.LocalDate;
import java.util.List;

public record EstudianteDTO(
    int id,
    String nombre,
    int anyoCurso,
    LocalDate fechaNacimiento,
    String casa,
    MascotaDTO mascota,
    List<AsignaturaCalificacionDTO> asignaturas
) {}
