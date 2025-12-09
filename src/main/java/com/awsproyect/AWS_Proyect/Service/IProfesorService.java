package com.awsproyect.AWS_Proyect.Service;

import com.awsproyect.AWS_Proyect.Models.Profesor;
import com.awsproyect.AWS_Proyect.Models.Request.ProfesorDTO;
import com.awsproyect.AWS_Proyect.Models.Request.UpdateProfesorResponseDTO;

import java.util.ArrayList;
import java.util.Optional;

public interface IProfesorService {
    ArrayList<Profesor> getProfesores();
    Optional<Profesor> getProfesorById(Long id);
    Long createProfesor(ProfesorDTO profesorDTO);
    Optional<Profesor> updateProfesor (Long id, UpdateProfesorResponseDTO profesor);

    boolean deleteProfesor(Long id);
}
