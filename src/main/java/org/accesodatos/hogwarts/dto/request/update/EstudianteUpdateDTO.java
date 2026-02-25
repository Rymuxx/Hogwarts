package org.accesodatos.hogwarts.dto.request.update;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EstudianteUpdateDTO {

    @NotNull(message = "El año de curso no puede ser nulo")
    @Min(value = 1, message = "El año de curso debe ser al menos 1")
    @Max(value = 7, message = "El año de curso no puede ser superior a 7")
    private Integer anyoCurso;

    @NotNull(message = "La fecha de nacimiento no puede ser nula")
    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El ID de la asignatura no puede ser nulo")
    private int asignaturaId;

    @Valid
    private MascotaUpdateDTO mascota;
}
