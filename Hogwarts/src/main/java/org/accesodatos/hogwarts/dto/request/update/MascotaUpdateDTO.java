package org.accesodatos.hogwarts.dto.request.update;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MascotaUpdateDTO {
    @NotBlank(message = "El nombre de la mascota no puede estar vacío")
    private String nombre;

    @NotBlank(message = "La especie de la mascota no puede estar vacía")
    private String especie;
}
