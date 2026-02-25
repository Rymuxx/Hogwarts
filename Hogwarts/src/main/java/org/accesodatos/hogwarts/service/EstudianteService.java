package org.accesodatos.hogwarts.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.accesodatos.hogwarts.dto.request.create.EstudianteCreateDTO;
import org.accesodatos.hogwarts.dto.request.update.EstudianteUpdateDTO;
import org.accesodatos.hogwarts.dto.response.EstudianteDTO;
import org.accesodatos.hogwarts.mapper.EstudianteMapper;
import org.accesodatos.hogwarts.mapper.MascotaMapper;
import org.accesodatos.hogwarts.model.Casa;
import org.accesodatos.hogwarts.model.Estudiante;
import org.accesodatos.hogwarts.model.Mascota;
import org.accesodatos.hogwarts.repository.CasaRepository;
import org.accesodatos.hogwarts.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final CasaRepository casaRepository;
    private final EstudianteMapper estudianteMapper;
    private final MascotaMapper mascotaMapper;

    public List<EstudianteDTO> findAll() {
        return estudianteRepository.findAll().stream()
                .map(estudianteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<EstudianteDTO> findById(int id) {
        return estudianteRepository.findById(id)
                .map(estudianteMapper::toDTO);
    }

    @Transactional
    public EstudianteDTO crear(EstudianteCreateDTO dto) {
        Casa casa = casaRepository.findById(dto.getCasaId())
                .orElseThrow(() -> new EntityNotFoundException("Casa no encontrada con id: " + dto.getCasaId()));

        Estudiante estudiante = estudianteMapper.toEntity(dto);
        estudiante.setCasa(casa);

        if (dto.getMascota() != null) {
            Mascota mascota = mascotaMapper.toEntity(dto.getMascota());
            mascota.setEstudiante(estudiante);
            estudiante.setMascota(mascota);
        }

        Estudiante guardado = estudianteRepository.save(estudiante);
        return estudianteMapper.toDTO(guardado);
    }

    @Transactional
    public EstudianteDTO actualizar(int id, EstudianteUpdateDTO dto) {
        Estudiante estudiante = estudianteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id));

        // Actualizamos solo los campos permitidos
        estudiante.setAnyoCurso(dto.getAnyoCurso());
        if (dto.getFechaNacimiento() != null) {
            estudiante.setFechaNacimiento(java.sql.Date.valueOf(dto.getFechaNacimiento()));
        }

        if (dto.getMascota() == null) {
            // Eliminar mascota si existía
            if (estudiante.getMascota() != null) {
                estudiante.getMascota().setEstudiante(null); // Romper la relación
                estudiante.setMascota(null);
            }
        } else {
            Mascota mascota;
            if (estudiante.getMascota() != null) {
                mascota = estudiante.getMascota();
                mascota.setNombre(dto.getMascota().getNombre());
                mascota.setEspecie(dto.getMascota().getEspecie());
            } else {
                mascota = new Mascota();
                mascota.setNombre(dto.getMascota().getNombre());
                mascota.setEspecie(dto.getMascota().getEspecie());
                mascota.setEstudiante(estudiante);
                estudiante.setMascota(mascota);
            }
        }

        Estudiante actualizado = estudianteRepository.save(estudiante);
        return estudianteMapper.toDTO(actualizado);
    }

    @Transactional
    public void deleteById(int id) {
        if (!estudianteRepository.existsById(id)) {
            throw new EntityNotFoundException("Estudiante no encontrado con id: " + id);
        }
        estudianteRepository.deleteById(id);
    }
}
