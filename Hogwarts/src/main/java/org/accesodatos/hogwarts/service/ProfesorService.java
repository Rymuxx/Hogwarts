package org.accesodatos.hogwarts.service;

import lombok.RequiredArgsConstructor;
import org.accesodatos.hogwarts.dto.response.ProfesorDTO;
import org.accesodatos.hogwarts.mapper.DtoMapper;
import org.accesodatos.hogwarts.model.Profesor;
import org.accesodatos.hogwarts.repository.ProfesorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfesorService {

    private final ProfesorRepository profesorRepository;
    private final DtoMapper dtoMapper;

    public List<ProfesorDTO> findAll() {
        return profesorRepository.findAll().stream()
                .map(dtoMapper::toProfesorDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProfesorDTO> findById(int id) {
        return profesorRepository.findById(id)
                .map(dtoMapper::toProfesorDTO);
    }

    public Profesor save(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public void deleteById(int id) {
        profesorRepository.deleteById(id);
    }
}
