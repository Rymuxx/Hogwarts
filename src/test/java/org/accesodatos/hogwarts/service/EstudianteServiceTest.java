package org.accesodatos.hogwarts.service;

import org.accesodatos.hogwarts.model.Estudiante;
import org.accesodatos.hogwarts.repository.EstudianteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstudianteServiceTest {

    @Mock
    private EstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteService estudianteService;

    private Estudiante harryPotter;

    @BeforeEach
    void setUp() {
        harryPotter = new Estudiante();
        harryPotter.setId(1);
        harryPotter.setNombre("Harry");
        harryPotter.setApellido("Potter");
        harryPotter.setAnyoCurso(5);
    }

    @Test
    void testExpulsarEstudiante() {
        when(estudianteRepository.existsById(1)).thenReturn(true);
        doNothing().when(estudianteRepository).deleteById(1);

        estudianteService.deleteById(1);

        verify(estudianteRepository, times(1)).deleteById(1);
    }
}
