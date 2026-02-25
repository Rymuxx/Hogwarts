package org.accesodatos.hogwarts.service;

import lombok.RequiredArgsConstructor;
import org.accesodatos.hogwarts.dto.response.CasaDTO;
import org.accesodatos.hogwarts.mapper.DtoMapper;
import org.accesodatos.hogwarts.model.Casa;
import org.accesodatos.hogwarts.repository.CasaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CasaService {

    private final CasaRepository casaRepository;
    private final DtoMapper dtoMapper;

    public List<CasaDTO> findAll() {
        return casaRepository.findAll().stream()
                .map(dtoMapper::toCasaDTO)
                .collect(Collectors.toList());
    }

    public Optional<CasaDTO> findById(int id) {
        return casaRepository.findById(id)
                .map(dtoMapper::toCasaDTO);
    }

    public Casa save(Casa casa) {
        return casaRepository.save(casa);
    }

    public void deleteById(int id) {
        casaRepository.deleteById(id);
    }
}
