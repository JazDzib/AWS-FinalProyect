package com.awsproyect.AWS_Proyect.Service;

import com.awsproyect.AWS_Proyect.Datos.MemoryData;
import com.awsproyect.AWS_Proyect.Models.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    MemoryData memoryData;

    public List<Alumno> getAlumnosList(){
        return memoryData.alumnosList;
    }

    public Alumno createAlumno (Alumno alumno){
        memoryData.alumnosList.add(alumno);
        return alumno;
    }

    // Actualizar alumno por id PUT
    public Optional<Alumno> updateAlumno (Long id, Alumno alumno){
        Optional<Alumno> encontrado = getAlumnoById(id);
        if (encontrado.isPresent()){
            Alumno actualizar = encontrado.get();
            actualizar.setNombres(alumno.getNombres());
            actualizar.setApellidos(alumno.getApellidos());
            actualizar.setMatricula(alumno.getMatricula());
            actualizar.setPromedio(alumno.getPromedio());
            return Optional.of(actualizar);
        }else {
            return Optional.empty();
        }
    }

    //Eliminar alumno por id DELETE
    public boolean deleteAlumno (Long id){
       Optional<Alumno> alumno = getAlumnoById(id);
       if(alumno.isPresent()){
           memoryData.alumnosList.remove(alumno.get());
           return true;
       }else {
           return false;
       }
    }

    public Optional<Alumno> getAlumnoById(Long id){
        return memoryData.alumnosList.stream()
                .filter(alumno -> alumno.getId().equals(id))
                .findFirst();
    }


}

