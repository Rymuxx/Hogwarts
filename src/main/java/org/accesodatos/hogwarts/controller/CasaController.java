package org.accesodatos.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.accesodatos.hogwarts.dto.response.CasaDTO;
import org.accesodatos.hogwarts.model.Casa;
import org.accesodatos.hogwarts.service.CasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Casas", description = "Gestión de las casas de Hogwarts (Gryffindor, Slytherin, etc.)")
@RestController
@RequestMapping("/casas")
public class CasaController {

    private final CasaService casaService;

    @Autowired
    public CasaController(CasaService casaService) {
        this.casaService = casaService;
    }

    @Operation(summary = "Obtener todas las casas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de casas devuelta correctamente")
    })
    @GetMapping
    public List<CasaDTO> getAllCasas() {
        return casaService.findAll();
    }

    @Operation(summary = "Obtener una casa por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Casa encontrada"),
            @ApiResponse(responseCode = "404", description = "Casa no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CasaDTO> getCasaById(@PathVariable int id) {
        return casaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear una nueva casa")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Casa creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<Casa> createCasa(@RequestBody Casa casa) {
        Casa savedCasa = casaService.save(casa);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCasa);
    }

    @Operation(summary = "Actualizar una casa existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Casa actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Casa no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Casa> updateCasa(@PathVariable int id, @RequestBody Casa casa) {
        casa.setIdCasa((long) id);
        Casa updatedCasa = casaService.save(casa);
        return ResponseEntity.ok(updatedCasa);
    }

    @Operation(summary = "Eliminar una casa por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Casa eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Casa no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCasa(@PathVariable int id) {
        casaService.deleteById(id);
        return ResponseEntity.ok().<Void>build();
    }
}
