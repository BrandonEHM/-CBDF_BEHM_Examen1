package com.upiiz.empleados.services;

import com.upiiz.empleados.models.Empleado;
import com.upiiz.empleados.repository.EmpleadosRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadosService {
    EmpleadosRepository empleadosRepository;

    public EmpleadosService(EmpleadosRepository empleadosRepository) {
        this.empleadosRepository = empleadosRepository;
    }

    public List<Empleado> getAllEmpleados() {
        return this.empleadosRepository.obtenerTodas();
    }

    public Empleado getEmpleadoById(Long id) {
        return this.empleadosRepository.obtenerPorId(id);
    }

    public Empleado createEmpleado(Empleado empleado) {
        return this.empleadosRepository.guardar(empleado);
    }

    public Empleado updateEmpleado(Empleado empleado) {
        return this.empleadosRepository.actualizar(empleado);
    }

    public void deleteEmpleado(Long id) {
        this.empleadosRepository.eliminar(id);
    }
}

