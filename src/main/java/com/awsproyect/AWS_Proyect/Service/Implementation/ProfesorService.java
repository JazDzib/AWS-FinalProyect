package com.awsproyect.AWS_Proyect.Service.Implementation;

import com.awsproyect.AWS_Proyect.Datos.MemoryData;
import com.awsproyect.AWS_Proyect.Models.Profesor;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProfesorService {
    @Autowired
    MemoryData memoryData;

    public List<Profesor> getProfesoresList() {
        return null;
    }

    public Profesor createProfesor(Profesor profesor) {

        return null;
    }

    public Optional<Profesor> getProfesorById(Long id) {
        return null;
    }

    // Actualizar alumno por id PUT
    public Optional<Profesor> updateProfesor(Long id, Profesor profesor) {
        Optional<Profesor> encontrado = getProfesorById(id);
        if (encontrado.isPresent()) {
            Profesor actualizar = encontrado.get();
            actualizar.setNombres(profesor.getNombres());
            actualizar.setApellidos(profesor.getApellidos());
            actualizar.setNumeroEmpleado(profesor.getNumeroEmpleado());
            actualizar.setHorasClase(profesor.getHorasClase());
            return Optional.of(actualizar);
        } else {
            return Optional.empty();
        }
    }

    //Eliminar alumno por id DELETE
    public boolean deleteProfesor(Long id) {
        return false;
    }
}
