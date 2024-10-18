package com.upiiz.empleados.controllers;

import com.upiiz.empleados.models.Empleado;
import com.upiiz.empleados.services.EmpleadosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping({"/api/v1/empleados"})
@RestController
public class EmpleadosController {
    EmpleadosService empleadosService;

    public EmpleadosController(EmpleadosService empleadosService) {
        this.empleadosService = empleadosService;
    }

    @GetMapping
    public ResponseEntity<List<Empleado>> getEmpleados() {
        return ResponseEntity.ok(this.empleadosService.getAllEmpleados());
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Empleado> getEmpleado(@PathVariable Long id) {
        return ResponseEntity.ok(this.empleadosService.getEmpleadoById(id));
    }

    @PostMapping
    public ResponseEntity<Empleado> addEmpleado(@RequestBody Empleado empleado) {
        return ResponseEntity.ok(this.empleadosService.createEmpleado(empleado));
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<Empleado> updateEmpleado(@RequestBody Empleado empleado, @PathVariable Long id) {
        empleado.setId(id);
        return ResponseEntity.ok(this.empleadosService.updateEmpleado(empleado));
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        this.empleadosService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }

}



