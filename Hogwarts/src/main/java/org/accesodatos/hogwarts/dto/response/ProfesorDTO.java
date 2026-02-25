package org.accesodatos.hogwarts.dto.response;

import java.time.LocalDate;

public record ProfesorDTO(
    int id,
    String nombre,
    String asignatura,
    LocalDate fechaInicio
) {}
