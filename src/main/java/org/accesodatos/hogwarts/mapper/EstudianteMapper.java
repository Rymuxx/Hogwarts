package org.accesodatos.hogwarts.mapper;

import lombok.RequiredArgsConstructor;
import org.accesodatos.hogwarts.dto.request.create.EstudianteCreateDTO;
import org.accesodatos.hogwarts.dto.response.AsignaturaCalificacionDTO;
import org.accesodatos.hogwarts.dto.response.EstudianteDTO;
import org.accesodatos.hogwarts.model.Estudiante;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EstudianteMapper {

    private final MascotaMapper mascotaMapper;

    public Estudiante toEntity(EstudianteCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setAnyoCurso(dto.getAnyoCurso());
        if (dto.getFechaNacimiento() != null) {
            estudiante.setFechaNacimiento(Date.valueOf(dto.getFechaNacimiento()));
        }

        return estudiante;
    }

    public EstudianteDTO toDTO(Estudiante estudiante) {
        if (estudiante == null) {
            return null;
        }
        return new EstudianteDTO(
                estudiante.getId(),
                estudiante.getNombre() + " " + estudiante.getApellido(),
                estudiante.getAnyoCurso(),
                estudiante.getFechaNacimiento() != null ? estudiante.getFechaNacimiento().toLocalDate() : null,
                estudiante.getCasa() != null ? estudiante.getCasa().getNombre() : null,
                mascotaMapper.toDTO(estudiante.getMascota()),
                estudiante.getAsignaturas() != null ? estudiante.getAsignaturas().stream()
                        .map(ea -> new AsignaturaCalificacionDTO(
                                ea.getAsignatura().getNombre(),
                                ea.getCalificacion()))
                        .collect(Collectors.toList()) : Collections.emptyList());
    }
}
