package org.accesodatos.hogwarts.service;

import lombok.RequiredArgsConstructor;
import org.accesodatos.hogwarts.dto.response.MascotaDTO;
import org.accesodatos.hogwarts.mapper.DtoMapper;
import org.accesodatos.hogwarts.model.Mascota;
import org.accesodatos.hogwarts.repository.MascotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MascotaService {

    private final MascotaRepository mascotaRepository;
    private final DtoMapper dtoMapper;

    public List<MascotaDTO> findAll() {
        return mascotaRepository.findAll().stream()
                .map(dtoMapper::toMascotaDTO)
                .collect(Collectors.toList());
    }

    public Optional<MascotaDTO> findById(int id) {
        return mascotaRepository.findById(id)
                .map(dtoMapper::toMascotaDTO);
    }

    public Mascota save(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    public void deleteById(int id) {
        mascotaRepository.deleteById(id);
    }
}
