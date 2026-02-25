package org.accesodatos.hogwarts.repository;

import org.accesodatos.hogwarts.model.Casa;
import org.accesodatos.hogwarts.model.Estudiante;
import org.accesodatos.hogwarts.model.Mascota;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class EstudianteRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Test
    void eliminarEstudiante_DebeEliminarMascotaEnCascada() {
        Casa casa = new Casa();
        casa.setNombre("Gryffindor");
        entityManager.persist(casa);
        entityManager.flush();

        Estudiante estudiante = new Estudiante();
        estudiante.setNombre("Ron");
        estudiante.setApellido("Weasley");
        estudiante.setAnyoCurso(5);
        estudiante.setCasa(casa);

        Mascota mascota = new Mascota();
        mascota.setNombre("Scabbers");
        mascota.setEspecie("Rata");

        estudiante.setMascota(mascota);
        mascota.setEstudiante(estudiante);

        entityManager.persist(estudiante);
        entityManager.flush();
        Long idMascota = mascota.getIdMascota();

        estudianteRepository.delete(estudiante);
        entityManager.flush();
        entityManager.clear();

        assertThat(estudianteRepository.findById(estudiante.getId())).isEmpty();

        Mascota mascotaEnBD = entityManager.find(Mascota.class, idMascota);
        assertNull(mascotaEnBD, "La mascota debería haber sido borrada por la cascada");
    }
}
