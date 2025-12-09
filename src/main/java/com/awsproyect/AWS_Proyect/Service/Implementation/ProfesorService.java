package com.awsproyect.AWS_Proyect.Service.Implementation;

import com.awsproyect.AWS_Proyect.Datos.MemoryData;
import com.awsproyect.AWS_Proyect.Models.Profesor;
import com.awsproyect.AWS_Proyect.Models.Request.ProfesorDTO;
import com.awsproyect.AWS_Proyect.Models.Request.UpdateProfesorResponseDTO;
import com.awsproyect.AWS_Proyect.Repository.ProfesorRepository;
import com.awsproyect.AWS_Proyect.Service.IProfesorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class ProfesorService implements IProfesorService {

    public final ProfesorRepository profesorRepository;

    public ArrayList<Profesor> getProfesores() {
        return new ArrayList<Profesor>(profesorRepository.findAll());
    }

    public Long createProfesor(ProfesorDTO profesor) {
        var newProfesor = profesor.entity();
        return profesorRepository.save(newProfesor).getId();
    }

    public Optional<Profesor> getProfesorById(Long id) {
        return profesorRepository.findById(id);
    }

    // Actualizar alumno por id PUT
    public Optional<Profesor> updateProfesor(Long id, UpdateProfesorResponseDTO profesor) {
       var encontrado = profesorRepository.findById(id);
       if(encontrado.isEmpty()){
           return Optional.empty();
       }
       var actualProfesor = encontrado.get();
       var profesorRequest = profesor.entity();

       if(profesorRequest.getNombres()!=null && !profesorRequest.getNombres().isBlank()){
           actualProfesor.setNombres(profesorRequest.getNombres());
       }
       if(profesorRequest.getApellidos()!=null && !profesorRequest.getApellidos().isBlank()){
           actualProfesor.setApellidos(profesorRequest.getApellidos());
       }
       if (profesorRequest.getNumeroEmpleado()!=null && !profesorRequest.getNumeroEmpleado().isBlank()) {
           actualProfesor.setNumeroEmpleado(profesorRequest.getNumeroEmpleado());
       }
       if (profesorRequest.getHorasClase() >0) {
           actualProfesor.setHorasClase(profesorRequest.getHorasClase());
       }

       profesorRepository.save(actualProfesor);
       return Optional.of(actualProfesor);
    }

    //Eliminar alumno por id DELETE
    public boolean deleteProfesor(Long id) {
        var encontrado = profesorRepository.findById(id);
        if(encontrado.isPresent()){
            profesorRepository.deleteById(id);
            return true;
        }else {
            return false;
        }
    }
}
