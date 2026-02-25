package org.accesodatos.hogwarts.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.accesodatos.hogwarts.dto.response.MascotaDTO;
import org.accesodatos.hogwarts.model.Mascota;
import org.accesodatos.hogwarts.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Mascotas", description = "Gestión de las mascotas de los estudiantes de Hogwarts")
@RestController
@RequestMapping("/mascotas")
public class MascotaController {

    private final MascotaService mascotaService;

    @Autowired
    public MascotaController(MascotaService mascotaService) {
        this.mascotaService = mascotaService;
    }

    @Operation(summary = "Obtener todas las mascotas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de mascotas devuelta correctamente")
    })
    @GetMapping
    public List<MascotaDTO> getAllMascotas() {
        return mascotaService.findAll();
    }

    @Operation(summary = "Obtener una mascota por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mascota encontrada"),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MascotaDTO> getMascotaById(@PathVariable int id) {
        return mascotaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Registrar una nueva mascota")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Mascota registrada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<Mascota> createMascota(@RequestBody Mascota mascota) {
        Mascota savedMascota = mascotaService.save(mascota);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMascota);
    }

    @Operation(summary = "Actualizar una mascota existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mascota actualizada correctamente"),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Mascota> updateMascota(@PathVariable int id, @RequestBody Mascota mascota) {
        mascota.setIdMascota((long) id);
        Mascota updatedMascota = mascotaService.save(mascota);
        return ResponseEntity.ok(updatedMascota);
    }

    @Operation(summary = "Eliminar una mascota por su ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mascota eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Mascota no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable int id) {
        mascotaService.deleteById(id);
        return ResponseEntity.ok().<Void>build();
    }
}
