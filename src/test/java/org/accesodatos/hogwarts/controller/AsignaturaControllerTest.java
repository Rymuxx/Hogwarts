package org.accesodatos.hogwarts.controller;

import org.accesodatos.hogwarts.exceptions.GlobalExceptionHandler;
import org.accesodatos.hogwarts.service.AsignaturaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ AsignaturaController.class, GlobalExceptionHandler.class })
public class AsignaturaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AsignaturaService asignaturaService;

    @Test
    void testEliminarAsignaturaConAlumnos() throws Exception {
        int asignaturaId = 1;
        doThrow(new DataIntegrityViolationException("Error de integridad"))
                .when(asignaturaService).deleteById(asignaturaId);

        mockMvc.perform(delete("/asignaturas/{id}", asignaturaId))
                .andExpect(status().isConflict());
    }
}
