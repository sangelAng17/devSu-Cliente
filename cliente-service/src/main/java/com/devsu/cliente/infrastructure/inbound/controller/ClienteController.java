package com.devsu.cliente.infrastructure.inbound.controller;

import com.devsu.cliente.application.ClienteService;
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
@RequestMapping("/Cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;
    @Operation(
            summary = "Consulta total de clientes",
            description = "Servicio usado para la consulta de todos client los registradas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de usuarios"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @GetMapping("/all")
    public ResponseEntity<Response<List<Cliente>>> findAll() {

        List<Cliente> result = clienteService.findAll();

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
            summary = "Consulta de clientes  por id",
            description = "Servicio usado para la consulta un cliente  por un Id espeficico"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response<Cliente>> findUser(@PathVariable Long id) {

        Optional<Cliente> result = clienteService.findById(id);

        if (result.isPresent()) {
            return ResponseEntity.ok(
                    Response.success(result.get(), "Usuario encontrado")
            );
        }

        return ResponseEntity.ok(
                Response.<Cliente>empty("No se encontraron valores")
        );
    }

    @Operation(
            summary = "Servicio usado para registrar clientes ",
            description = "Retorna la insercion de un cliente enviado para su debido registro"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario registrado"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @PostMapping("/register")
    public ResponseEntity<Response<Cliente>> findAll(@RequestBody Cliente cliente) {

        Cliente result = clienteService.insert(cliente);

        if (result.getId()==null) {
            return ResponseEntity.ok(
                    Response.empty("No se encontraron usuarios")
            );
        }

        return ResponseEntity.ok(
                Response.success(result, "Usuarios encontrados")
        );
    }


    @Operation(
            summary = "Servicio usado para actualizar clientes ",
            description = "Retorna la actualizacion de un cliente enviado para su debida actualizacion"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @PutMapping("/update")
    public ResponseEntity<Response<Cliente>> update(@RequestBody Cliente cliente) {

        Cliente result = clienteService.update(cliente);

        if (result.getId()==null) {
            return ResponseEntity.ok(
                    Response.empty("No se encontraron personas ")
            );
        }

        return ResponseEntity.ok(
                Response.success(result, "Actualizacion exitosa")
        );
    }

    @Operation(
            summary = "Servicio usado para borrar de un cliente",
            description = "Asegura el borrado de un cliente enviado"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
            @ApiResponse(responseCode = "400", description = "Parámetro inválido")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<Persona>> delete(@PathVariable Long id) {

        clienteService.delete(id);

        return ResponseEntity.ok(
                Response.success(null, "Borrado exitosamente")
        );
    }


}
