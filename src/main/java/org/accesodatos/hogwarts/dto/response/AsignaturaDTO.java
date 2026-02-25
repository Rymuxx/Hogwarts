package org.accesodatos.hogwarts.dto.response;

public record AsignaturaDTO(
    Long id,
    String nombre,
    String aula,
    Boolean obligatoria,
    String profesor
) {}
