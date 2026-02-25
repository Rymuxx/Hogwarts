package org.accesodatos.hogwarts.service;

import lombok.RequiredArgsConstructor;
import org.accesodatos.hogwarts.dto.response.AsignaturaDTO;
import org.accesodatos.hogwarts.mapper.DtoMapper;
import org.accesodatos.hogwarts.model.Asignatura;
import org.accesodatos.hogwarts.repository.AsignaturaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AsignaturaService {

    private final AsignaturaRepository asignaturaRepository;
    private final DtoMapper dtoMapper;

    public List<AsignaturaDTO> findAll() {
        return asignaturaRepository.findAll().stream()
                .map(dtoMapper::toAsignaturaDTO)
                .collect(Collectors.toList());
    }

    public Optional<AsignaturaDTO> findById(int id) {
        return asignaturaRepository.findById(id)
                .map(dtoMapper::toAsignaturaDTO);
    }

    public Asignatura save(Asignatura asignatura) {
        return asignaturaRepository.save(asignatura);
    }

    public void deleteById(int id) {
        asignaturaRepository.deleteById(id);
    }
}
