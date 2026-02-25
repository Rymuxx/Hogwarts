package org.accesodatos.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.accesodatos.hogwarts.dto.request.create.EstudianteCreateDTO;
import org.accesodatos.hogwarts.dto.request.update.EstudianteUpdateDTO;
import org.accesodatos.hogwarts.dto.response.EstudianteDTO;
import org.accesodatos.hogwarts.service.EstudianteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Estudiantes", description = "Gestión de los estudiantes de Hogwarts")
@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService estudianteService;

    @Operation(summary = "Obtener todos los estudiantes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de estudiantes devuelta correctamente")
    })
    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> getAllEstudiantes() {
        return ResponseEntity.ok(estudianteService.findAll());
    }

    @Operation(summary = "Obtener un estudiante por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estudiante encontrado"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> getEstudianteById(@PathVariable int id) {
        return estudianteService.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con id: " + id));
    }

    @Operation(summary = "Crear un nuevo estudiante")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Estudiante creado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstudianteDTO> crearEstudiante(@Valid @RequestBody EstudianteCreateDTO dto) {
        EstudianteDTO creado = estudianteService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @Operation(summary = "Actualizar completamente un estudiante existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Estudiante actualizado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(@PathVariable int id,
            @Valid @RequestBody EstudianteUpdateDTO dto) {
        EstudianteDTO actualizado = estudianteService.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }

    @Operation(summary = "Eliminar un estudiante por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Estudiante eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable int id) {
        estudianteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
