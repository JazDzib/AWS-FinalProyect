package com.awsproyect.AWS_Proyect.Datos;

import com.awsproyect.AWS_Proyect.Models.Alumno;
import com.awsproyect.AWS_Proyect.Models.Profesor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MemoryData {
    public List<Alumno> alumnosList = new ArrayList<>(List.of(
            // new Alumno(Long id, String nombres, String apellidos, String matricula, Double promedio)
            new Alumno(1L, "Nuri", "Esquivel", "A001", 9.5),
            new Alumno(2L, "Juan", "Perez", "A002", 8.0),
            new Alumno(3L, "Maria", "Garcia", "A003", 9.0),
            new Alumno(4L, "Luis", "Solis", "A004", 7.5),
            new Alumno(5L, "Ana", "Lopez", "A005", 8.8)
    ));

    // --- Lista de Profesores ---
    public List<Profesor> profesoresList = new ArrayList<>(List.of(
            // new Profesor(Long id, String numeroEmpleado, String nombres, String apellidos, int horasClase)
            new Profesor(1L, "P001", "Alan", "Turing", 20),
            new Profesor(2L, "P002", "Rosa", "Luxemburgo", 15),
            new Profesor(3L, "P003", "Miguel", "Cervantes", 18),
            new Profesor(4L, "P004", "Carla", "Sagan", 22),
            new Profesor(5L, "P005", "David", "Hilbert", 12)
    ));

}
