package org.accesodatos.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.accesodatos.hogwarts.dto.response.ProfesorDTO;
import org.accesodatos.hogwarts.model.Profesor;
import org.accesodatos.hogwarts.service.ProfesorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Profesores", description = "Gestión de los profesores del colegio Hogwarts")
@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    @Autowired
    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @Operation(summary = "Obtener todos los profesores")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de profesores devuelta correctamente")
    })
    @GetMapping
    public List<ProfesorDTO> getAllProfesores() {
        return profesorService.findAll();
    }

    @Operation(summary = "Obtener un profesor por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profesor encontrado"),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfesorDTO> getProfesorById(@PathVariable int id) {
        return profesorService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar un nuevo profesor")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Profesor registrado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<Profesor> createProfesor(@RequestBody Profesor profesor) {
        Profesor savedProfesor = profesorService.save(profesor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProfesor);
    }

    @Operation(summary = "Actualizar un profesor existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profesor actualizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Profesor> updateProfesor(@PathVariable int id, @RequestBody Profesor profesor) {
        profesor.setIdProfesor((long) id);
        Profesor updatedProfesor = profesorService.save(profesor);
        return ResponseEntity.ok(updatedProfesor);
    }

    @Operation(summary = "Eliminar un profesor por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Profesor eliminado correctamente"),
            @ApiResponse(responseCode = "404", description = "Profesor no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor(@PathVariable int id) {
        profesorService.deleteById(id);
        return ResponseEntity.ok().<Void>build();
    }
}
