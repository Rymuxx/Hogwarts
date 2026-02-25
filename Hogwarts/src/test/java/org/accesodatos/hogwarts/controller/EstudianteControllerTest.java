package org.accesodatos.hogwarts.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.accesodatos.hogwarts.dto.request.create.EstudianteCreateDTO;
import org.accesodatos.hogwarts.exceptions.GlobalExceptionHandler;
import org.accesodatos.hogwarts.service.EstudianteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({ EstudianteController.class, GlobalExceptionHandler.class })
public class EstudianteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EstudianteService estudianteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearEstudianteConAnyoCursoInvalido() throws Exception {
        EstudianteCreateDTO estudianteCreateDTO = new EstudianteCreateDTO();
        estudianteCreateDTO.setNombre("Draco");
        estudianteCreateDTO.setApellido("Malfoy");
        estudianteCreateDTO.setAnyoCurso(10);
        estudianteCreateDTO.setCasaId(1);

        mockMvc.perform(post("/api/estudiantes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(estudianteCreateDTO)))
                .andExpect(status().isBadRequest());
    }
}
