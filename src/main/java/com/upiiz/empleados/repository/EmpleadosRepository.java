package com.upiiz.empleados.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.upiiz.empleados.models.Empleado;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class EmpleadosRepository {
    private static final String FILE_PATH = "src/main/resources/empleados.json";
    private List<Empleado> empleados = new ArrayList<>();
    private AtomicLong id = new AtomicLong();

    public EmpleadosRepository() {
        // Cargar empleados del archivo JSON cuando se inicialice el repositorio
        this.leerEmpleadosDeArchivo();
    }

    // Método para guardar un nuevo empleado
    public Empleado guardar(Empleado empleado) {
        empleado.setId(this.id.incrementAndGet());
        this.empleados.add(empleado);
        this.guardarEmpleadosEnArchivo();  // Guardar cambios en el archivo JSON
        return empleado;
    }

    // Método para obtener todos los empleados
    public List<Empleado> obtenerTodas() {
        return this.empleados;
    }

    // Método para obtener un empleado por su ID
    public Empleado obtenerPorId(Long id) {
        return this.empleados.stream().filter(empleado -> empleado.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // Método para eliminar un empleado por su ID
    public void eliminar(Long id) {
        this.empleados.removeIf(empleado -> empleado.getId().equals(id));
        this.guardarEmpleadosEnArchivo();  // Guardar cambios en el archivo JSON
    }

    // Método para actualizar un empleado
    public Empleado actualizar(Empleado empleado) {
        this.eliminar(empleado.getId());
        this.empleados.add(empleado);
        this.guardarEmpleadosEnArchivo();  // Guardar cambios en el archivo JSON
        return empleado;
    }

    // Método privado para leer los empleados desde el archivo JSON
    private void leerEmpleadosDeArchivo() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Registrar el módulo para manejar Java 8 fechas
            objectMapper.registerModule(new JavaTimeModule());

            File archivo = new File(FILE_PATH);
            if (archivo.exists()) {
                Empleado[] empleadosArray = objectMapper.readValue(archivo, Empleado[].class);
                this.empleados = new ArrayList<>(Arrays.asList(empleadosArray));

                // Ajustar el contador de IDs al último ID en la lista
                if (!this.empleados.isEmpty()) {
                    Long maxId = this.empleados.stream().mapToLong(Empleado::getId).max().orElse(0L);
                    this.id.set(maxId);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo JSON", e);
        }
    }

    // Método privado para guardar los empleados en el archivo JSON
    private void guardarEmpleadosEnArchivo() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            // Registrar el módulo para manejar Java 8 fechas
            objectMapper.registerModule(new JavaTimeModule());

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), this.empleados);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el archivo JSON", e);
        }
    }
}
