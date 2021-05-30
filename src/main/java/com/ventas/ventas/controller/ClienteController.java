package com.ventas.ventas.controller;


import com.ventas.ventas.model.Cliente;
import com.ventas.ventas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    //Inyectamos la dependencia de ClienteService
    @Autowired
    private ClienteService clienteService;

    //Metodo para obtener todos los clientes de ls BD
    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        return ResponseEntity.ok(clienteService.findAll());
    }

    //metodo para buscar un cliente por Id y si no existe manda un 404 notFound
    //recibimos un parametro por la url y con pathvariable lo asiganos al idCliente
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable("id") Integer idCliente){
        return clienteService.findById(idCliente)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    //Crear un cliente
    //requestbody mandamos los datos a insertar por el cuerpo de la consulta
    @PostMapping
    public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente cliente) {
        return new ResponseEntity<>(clienteService.create(cliente), HttpStatus.CREATED);
    }

    //modificar cliente
    //buscamos un cliene por id si lo encuentra en el map lo actualzia
    //caso contrario orElseGet devuelve un error 404
    @PutMapping
    public ResponseEntity<Cliente> update(@Valid @RequestBody Cliente cliente) {
        return clienteService.findById(cliente.getIdCliente())
                .map(c -> ResponseEntity.ok(clienteService.update(cliente)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Eliminar Cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<Cliente> delete(@PathVariable("id") Integer idCliente){
        return clienteService.findById(idCliente)
                .map(c->{
                    clienteService.delete(idCliente);
                    return ResponseEntity.ok(c);
                })
                .orElseGet(()->ResponseEntity.notFound().build());
    }
}
