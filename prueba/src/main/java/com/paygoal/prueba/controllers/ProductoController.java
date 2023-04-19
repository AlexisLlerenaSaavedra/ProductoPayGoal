package com.paygoal.prueba.controllers;

import com.paygoal.prueba.models.Producto;
import com.paygoal.prueba.services.ProductoService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.awt.print.Book;
import java.util.List;
@OpenAPIDefinition(
        info = @Info(
                title = "Productos Paygoal",
                version = "1.0",
                description = "Este servicio es el responsable de realizar las operaciones CRUD Productos",
                contact = @Contact(
                        name = "Alexis Llerena Saavedra",
                        email = "alexisllerenasaa@gmail.com"
                )
        )
)
@RestController
@RequestMapping(path = "api/v1/productos")
public class ProductoController{
    @Autowired
    ProductoService productoService;


    //Swagger
    @Operation(summary = "Obtener todos los productos ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de zones obtenida correctamente"),

            @ApiResponse(responseCode = "404", description = "Error en la solicitud"),

            @ApiResponse(responseCode = "500", description = "algo ha fallado en el sitio web")})
    //

    @GetMapping("")
    public ResponseEntity<?>getAll(){
    try {
        return ResponseEntity.status(HttpStatus.OK).body(productoService.findAllProducto());
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
    }

    //Swagger
    @Operation(summary = "Crear Producto ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado correctamente"),

            @ApiResponse(responseCode = "400", description = "Error en cliente"),

            @ApiResponse(responseCode = "500", description = "algo ha fallado en el sitio web")})
    //

    @PostMapping("")
    public ResponseEntity<?> saveProducto(@RequestBody Producto producto){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(productoService.saveProducto(producto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Swagger
    @Operation(summary = "Actualizar producto ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),

            @ApiResponse(responseCode = "404", description = "Error en la solicitud"),

            @ApiResponse(responseCode = "500", description = "algo ha fallado en el sitio web")})
    //

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable long id,@RequestBody Producto producto){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.updateProducto(id, producto));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Swagger
    @Operation(summary = "Eliminar producto ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto no encotrado, eliminado correctamente"),

            @ApiResponse(responseCode = "404", description = "Error en la solicitud"),

            @ApiResponse(responseCode = "500", description = "algo ha fallado en el sitio web")})
    //

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable long id ){
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(productoService.deleteProducto(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //Swagger
    @Operation(summary = "Obtener producto por ID ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto obtenido por ID correctamente"),

            @ApiResponse(responseCode = "404", description = "No se pudo encontrar el producto"),

            @ApiResponse(responseCode = "500", description = "algo ha fallado en el sitio web")})
    //

    @GetMapping("producto/{id}")
    public ResponseEntity<?> getByIdProducto(@PathVariable long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.findByIdProducto(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }

    }

    //Swagger
    @Operation(summary = "Obtener productos por nombre ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de zones obtenida correctamente"),

            @ApiResponse(responseCode = "404", description = "No se encontro producto con este nombre"),

            @ApiResponse(responseCode = "500", description = "algo ha fallado en el sitio web")})
    //
    @GetMapping("/search")
    public ResponseEntity<?> getByNameProducto(@RequestParam String name ){

        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.findByNameProducto(name));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    //Swagger
    @Operation(summary = "Obtener productos por orden asc ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos ordenador asc correctamente"),

            @ApiResponse(responseCode = "404", description = "No se pudo ordenar productos"),

            @ApiResponse(responseCode = "500", description = "algo ha fallado en el sitio web")})
    //

    @GetMapping("/orderprice")
    public ResponseEntity<?> getByOrderPriceProducto(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productoService.findAllByOrderByPriceAsc());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron productos por ordenar");
        }
    }
}
