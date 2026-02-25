package org.accesodatos.hogwarts.mapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.accesodatos.hogwarts.dto.request.create.EstudianteCreateDTO;
import org.accesodatos.hogwarts.dto.request.update.EstudianteUpdateDTO;
import org.accesodatos.hogwarts.dto.response.*;
import org.accesodatos.hogwarts.model.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
@RequiredArgsConstructor
public class DtoMapper {

    public CasaDTO toCasaDTO(Casa casa) {
        if (casa == null)
            return null;
        return new CasaDTO(
                casa.getIdCasa().intValue(),
                casa.getNombre(),
                casa.getFundador(),
                casa.getFantasma(),
                this.toProfesorDTO(casa.getJefeDeCasa()),
                casa.getEstudiantes() != null ? casa.getEstudiantes().stream()
                        .map(e -> e.getNombre() + " " + e.getApellido())
                        .collect(Collectors.toList()) : Collections.emptyList());
    }

    public ProfesorDTO toProfesorDTO(Profesor profesor) {
        if (profesor == null)
            return null;
        return new ProfesorDTO(
                profesor.getIdProfesor().intValue(),
                profesor.getNombre() + " " + profesor.getApellido(),
                profesor.getAsignatura() != null ? profesor.getAsignatura().getNombre() : null,
                profesor.getFechaInicio());
    }

    public MascotaDTO toMascotaDTO(Mascota mascota) {
        if (mascota == null)
            return null;
        return new MascotaDTO(
                mascota.getIdMascota().intValue(),
                mascota.getNombre(),
                mascota.getEspecie(),
                mascota.getEstudiante() != null
                        ? mascota.getEstudiante().getNombre() + " " + mascota.getEstudiante().getApellido()
                        : null);
    }

    public AsignaturaDTO toAsignaturaDTO(Asignatura asignatura) {
        if (asignatura == null)
            return null;
        return new AsignaturaDTO(
                (long) asignatura.getId(),
                asignatura.getNombre(),
                asignatura.getAula(),
                asignatura.isObligatoria(),
                asignatura.getProfesor() != null
                        ? asignatura.getProfesor().getNombre() + " " + asignatura.getProfesor().getApellido()
                        : null);
    }

    public EstudianteDTO toEstudianteDTO(Estudiante estudiante) {
        if (estudiante == null)
            return null;

        MascotaDTO mascotaDTO = null;
        if (estudiante.getMascota() != null) {
            mascotaDTO = this.toMascotaDTO(estudiante.getMascota());
        }

        List<AsignaturaCalificacionDTO> asignaturasDTO = Collections.emptyList();
        if (estudiante.getAsignaturas() != null) {
            asignaturasDTO = estudiante.getAsignaturas().stream()
                    .map(ea -> new AsignaturaCalificacionDTO(
                            ea.getAsignatura().getNombre(),
                            ea.getCalificacion()))
                    .collect(Collectors.toList());
        }

        return new EstudianteDTO(
                estudiante.getId(),
                estudiante.getNombre() + " " + estudiante.getApellido(),
                estudiante.getAnyoCurso(),
                estudiante.getFechaNacimiento() != null ? estudiante.getFechaNacimiento().toLocalDate() : null,
                estudiante.getCasa() != null ? estudiante.getCasa().getNombre() : null,
                mascotaDTO,
                asignaturasDTO);
    }

    public Estudiante toEstudiante(EstudianteCreateDTO dto, Casa casa) {
        if (dto == null)
            return null;

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre(dto.getNombre());
        estudiante.setApellido(dto.getApellido());
        estudiante.setAnyoCurso(dto.getAnyoCurso());
        if (dto.getFechaNacimiento() != null) {
            estudiante.setFechaNacimiento(Date.valueOf(dto.getFechaNacimiento()));
        }
        estudiante.setCasa(casa);

        if (dto.getMascota() != null) {
            Mascota mascota = new Mascota();
            mascota.setNombre(dto.getMascota().getNombre());
            mascota.setEspecie(dto.getMascota().getEspecie());
            mascota.setEstudiante(estudiante);
            estudiante.setMascota(mascota);
        }

        return estudiante;
    }

    public void updateEstudianteFromDto(EstudianteUpdateDTO dto, Estudiante estudiante) {
        if (dto == null || estudiante == null)
            return;

        estudiante.setAnyoCurso(dto.getAnyoCurso());
        if (dto.getFechaNacimiento() != null) {
            estudiante.setFechaNacimiento(Date.valueOf(dto.getFechaNacimiento()));
        }

        if (dto.getMascota() != null) {
            if (estudiante.getMascota() != null) {
                Mascota mascota = estudiante.getMascota();
                mascota.setNombre(dto.getMascota().getNombre());
                mascota.setEspecie(dto.getMascota().getEspecie());
            } else {
                Mascota mascota = new Mascota();
                mascota.setNombre(dto.getMascota().getNombre());
                mascota.setEspecie(dto.getMascota().getEspecie());
                mascota.setEstudiante(estudiante);
                estudiante.setMascota(mascota);
            }
        } else {
            if (estudiante.getMascota() != null) {
                estudiante.setMascota(null);
            }
        }
    }
}
