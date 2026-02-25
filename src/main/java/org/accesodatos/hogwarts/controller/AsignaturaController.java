package org.accesodatos.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.accesodatos.hogwarts.dto.response.AsignaturaDTO;
import org.accesodatos.hogwarts.model.Asignatura;
import org.accesodatos.hogwarts.service.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Asignaturas", description = "Gestión de las asignaturas impartidas en Hogwarts")
@RestController
@RequestMapping("/asignaturas")
public class AsignaturaController {

    private final AsignaturaService asignaturaService;

    @Autowired
    public AsignaturaController(AsignaturaService asignaturaService) {
        this.asignaturaService = asignaturaService;
    }

    @Operation(summary = "Obtener todas las asignaturas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de asignaturas devuelta correctamente")
    })
    @GetMapping
    public List<AsignaturaDTO> getAllAsignaturas() {
        return asignaturaService.findAll();
    }

    @Operation(summary = "Obtener una asignatura por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Asignatura encontrada"),
            @ApiResponse(responseCode = "404", description = "Asignatura no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaDTO> getAsignaturaById(@PathVariable int id) {
        return asignaturaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva asignatura")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Asignatura creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<Asignatura> createAsignatura(@RequestBody Asignatura asignatura) {
        Asignatura savedAsignatura = asignaturaService.save(asignatura);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAsignatura);
    }

    @Operation(summary = "Actualizar una asignatura existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Asignatura actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Asignatura no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Asignatura> updateAsignatura(@PathVariable int id, @RequestBody Asignatura asignatura) {
        asignatura.setId(id);
        Asignatura updatedAsignatura = asignaturaService.save(asignatura);
        return ResponseEntity.ok(updatedAsignatura);
    }

    @Operation(summary = "Eliminar una asignatura por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Asignatura eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Asignatura no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsignatura(@PathVariable int id) {
        asignaturaService.deleteById(id);
        return ResponseEntity.ok().<Void>build();
    }
}
