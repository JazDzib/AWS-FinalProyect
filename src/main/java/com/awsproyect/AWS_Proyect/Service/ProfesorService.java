package com.awsproyect.AWS_Proyect.Service;

import com.awsproyect.AWS_Proyect.Datos.MemoryData;
import com.awsproyect.AWS_Proyect.Models.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProfesorService {
    @Autowired
    MemoryData memoryData;

    public List<Profesor> getProfesoresList(){
        return memoryData.profesoresList;
    }

    public Profesor createProfesor (Profesor profesor){
        memoryData.profesoresList.add(profesor);
        return profesor;
    }

    public Optional<Profesor> getProfesorById(Long id){
        return memoryData.profesoresList.stream()
                .filter(profesor -> profesor.getId().equals(id))
                .findFirst();
    }

    // Actualizar alumno por id PUT
    public Optional<Profesor> updateProfesor (Long id, Profesor profesor){
        Optional<Profesor> encontrado = getProfesorById(id);
        if (encontrado.isPresent()){
            Profesor actualizar = encontrado.get();
            actualizar.setNombres(profesor.getNombres());
            actualizar.setApellidos(profesor.getApellidos());
            actualizar.setNumeroEmpleado(profesor.getNumeroEmpleado());
            actualizar.setHorasClase(profesor.getHorasClase());
            return Optional.of(actualizar);
        }else {
            return Optional.empty();
        }
    }

    //Eliminar alumno por id DELETE
    public boolean deleteProfesor (Long id){
        Optional<Profesor> profesor = getProfesorById(id);
        if(profesor.isPresent()){
            memoryData.profesoresList.remove(profesor.get());
            return true;
        }else {
            return false;
        }
    }



}
