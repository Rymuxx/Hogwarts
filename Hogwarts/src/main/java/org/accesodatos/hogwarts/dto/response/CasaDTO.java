package org.accesodatos.hogwarts.dto.response;

import java.util.List;

public record CasaDTO(
    int id,
    String nombre,
    String fundador,
    String fantasma,
    ProfesorDTO jefe,
    List<String> estudiantes
) {}
