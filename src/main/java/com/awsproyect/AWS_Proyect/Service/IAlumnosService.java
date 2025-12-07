package com.awsproyect.AWS_Proyect.Service;

import com.awsproyect.AWS_Proyect.Models.Alumno;
import com.awsproyect.AWS_Proyect.Models.Request.AlumnoDTO;
import com.awsproyect.AWS_Proyect.Models.Request.UpdateAlumnosRequestDTO;

import java.util.List;
import java.util.Optional;

public interface IAlumnosService {
    List<Alumno> getAlumnos();
    Optional<Alumno> getAlumnoById(Long id);
    Long createAlumno(AlumnoDTO alumno);
    Optional<Alumno> updateAlumno(Long id, UpdateAlumnosRequestDTO alumno);
    boolean deleteAlumno(Long id);
}
