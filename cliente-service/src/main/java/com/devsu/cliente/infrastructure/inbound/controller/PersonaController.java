package com.devsu.cliente.infrastructure.inbound.controller;

import com.devsu.cliente.application.PersonaService;
import com.devsu.cliente.domain.model.Cliente;
import com.devsu.cliente.domain.model.Persona;
import com.devsu.cliente.infrastructure.inbound.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Persona")
public class
PersonaController {

    @Autowired
    PersonaService personaService;
    @Operation(
            summary = "Consulta total de personas",
            description = "Servicio usado para la consulta todas las personas registradas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @GetMapping("/all")
    public ResponseEntity<Response<List<Persona>>> findAll() {

        List<Persona> result = personaService.findAll();

        if (result.isEmpty()) {
            return ResponseEntity.ok(
                    Response.empty("No se encontraron usuarios")
            );
        }

        return ResponseEntity.ok(
                Response.success(result, "Usuarios encontrados")
        );
    }

    @Operation(
            summary = "Consulta de personas por id",
            description = "Servicio usado para la consulta una persona por un Id espeficico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response<Persona>> findUser(@PathVariable Long id) {

        Optional<Persona> result = personaService.findUser(id);

        if (result.isPresent()) {
            return ResponseEntity.ok(
                    Response.success(result.get(), "Usuario encontrado")
            );
        }

        return ResponseEntity.ok(
                Response.<Persona>empty("No se encontraron valores")
        );
    }

    @Operation(
            summary = "Servicio usado para registrar Personas",
            description = "Retorna la insercion de la usuario enviado para su debido registro"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @PostMapping("/register")
    public ResponseEntity<Response<Persona>> findAll(@RequestBody Persona persona) {

        Persona result = personaService.insert(persona);

        if (result.getIdentificacion()==null) {
            return ResponseEntity.ok(
                    Response.empty("No se encontraron usuarios")
            );
        }

        return ResponseEntity.ok(
                Response.success(result, "Usuarios encontrados")
        );
    }


    @Operation(
            summary = "Servicio usado para actualizar personas ",
            description = "Retorna la actualizacion de la persona enviada para su debida actualizacion"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @PutMapping("/update")
    public ResponseEntity<Response<Persona>> update(@RequestBody Persona persona) {

        Persona result = personaService.update(persona);

        if (result.getIdentificacion()==null) {
            return ResponseEntity.ok(
                    Response.empty("No se encontraron personas ")
            );
        }

        return ResponseEntity.ok(
                Response.success(result, "Actualizacion exitosa")
        );
    }

    @Operation(
            summary = "Servicio usado para borrar de una persona",
            description = "Asegura el borrado de la persona enviada"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<Persona>> delete(@PathVariable Long id) {

        personaService.delete(id);

        return ResponseEntity.ok(
                Response.success(null, "Borrado exitosamente")
        );
    }

}
