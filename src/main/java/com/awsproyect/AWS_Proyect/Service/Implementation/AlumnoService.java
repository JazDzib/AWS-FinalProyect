package com.awsproyect.AWS_Proyect.Service.Implementation;

import com.awsproyect.AWS_Proyect.Datos.MemoryData;
import com.awsproyect.AWS_Proyect.Models.Alumno;
import com.awsproyect.AWS_Proyect.Models.Request.AlumnoDTO;
import com.awsproyect.AWS_Proyect.Models.Request.UpdateAlumnosRequestDTO;
import com.awsproyect.AWS_Proyect.Repository.AlumnosRepository;
import com.awsproyect.AWS_Proyect.Service.IAlumnosService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlumnoService implements IAlumnosService {

    private final AlumnosRepository alumnosRepository;

    public ArrayList<Alumno> getAlumnos(){
       return new ArrayList<Alumno>(alumnosRepository.findAll());
    }

    public Long createAlumno (AlumnoDTO alumno){
        var newAlumno = alumno.entity();
        return alumnosRepository.save(newAlumno).getId();
    }

    // Actualizar alumno por id PUT
    public Optional<Alumno> updateAlumno (Long id, UpdateAlumnosRequestDTO alumno){
        var encontrado = alumnosRepository.findById(id);
        if(encontrado.isEmpty()){
            return Optional.empty();
        }
        var actualizar = encontrado.get();
        var alumnoRequest = alumno.entity();

        if(alumnoRequest.getNombres()!=null&& !alumnoRequest.getNombres().isBlank()){
            actualizar.setNombres(alumnoRequest.getNombres());
        }
        if(alumnoRequest.getApellidos()!=null&& !alumnoRequest.getApellidos().isBlank()){
            actualizar.setApellidos(alumnoRequest.getApellidos());
        }
        if(alumnoRequest.getMatricula()!=null&& !alumnoRequest.getMatricula().isBlank()){
            actualizar.setMatricula(alumnoRequest.getMatricula());
        }
        if(alumnoRequest.getMatricula()!=null&& !alumnoRequest.getMatricula().isBlank()){
            actualizar.setMatricula(alumnoRequest.getMatricula());
        }
        if(alumnoRequest.getPromedio()!=null ){
            actualizar.setPromedio(alumnoRequest.getPromedio());
        }
        if(alumnoRequest.getFotoPerfilUrl()!=null&& !alumnoRequest.getFotoPerfilUrl().isBlank()){
            actualizar.setFotoPerfilUrl(alumnoRequest.getFotoPerfilUrl());
        }
        if(alumnoRequest.getPassword()!=null&& !alumnoRequest.getPassword().isBlank()){
            actualizar.setPassword(alumnoRequest.getPassword());
        }
        alumnosRepository.save(actualizar);
        return Optional.of(actualizar);
    }

    //Eliminar alumno por id DELETE
    public boolean deleteAlumno (Long id){
    Optional<Alumno> alumno = alumnosRepository.findById(id);
        if(alumno.isPresent()){
            alumnosRepository.delete(alumno.get());
            return true;
        }else {
            return false;
        }
    }

    public Optional<Alumno> getAlumnoById(Long id){
        return alumnosRepository.findById(id);
    }
}

