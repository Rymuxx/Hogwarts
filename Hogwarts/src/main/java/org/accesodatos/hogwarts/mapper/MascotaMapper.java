package org.accesodatos.hogwarts.mapper;

import org.accesodatos.hogwarts.dto.request.create.MascotaCreateDTO;
import org.accesodatos.hogwarts.dto.response.MascotaDTO;
import org.accesodatos.hogwarts.model.Mascota;
import org.springframework.stereotype.Component;

@Component
public class MascotaMapper {

    public Mascota toEntity(MascotaCreateDTO dto) {
        if (dto == null) {
            return null;
        }
        Mascota mascota = new Mascota();
        mascota.setNombre(dto.getNombre());
        mascota.setEspecie(dto.getEspecie());
        return mascota;
    }

    public MascotaDTO toDTO(Mascota mascota) {
        if (mascota == null) {
            return null;
        }
        return new MascotaDTO(
                mascota.getIdMascota().intValue(),
                mascota.getNombre(),
                mascota.getEspecie(),
                mascota.getEstudiante() != null ? (mascota.getEstudiante().getNombre() + " " + mascota.getEstudiante().getApellido()) : null
        );
    }
}
