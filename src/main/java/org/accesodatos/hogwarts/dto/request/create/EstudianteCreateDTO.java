package org.accesodatos.hogwarts.dto.request.create;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EstudianteCreateDTO {
    @NotBlank(message = "El nombre no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar vacío")
    private String apellido;

    @Min(value = 1, message = "El año de curso debe ser al menos 1")
    @Max(value = 7, message = "El año de curso no puede ser superior a 7")
    private int anyoCurso;

    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El ID de la casa no puede ser nulo")
    private int casaId;

    @NotNull(message = "El ID de la asignatura no puede ser nulo")
    private int asignaturaId;

    @Valid
    private MascotaCreateDTO mascota;
}
