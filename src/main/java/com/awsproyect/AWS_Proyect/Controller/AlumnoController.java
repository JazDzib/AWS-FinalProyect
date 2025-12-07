package com.awsproyect.AWS_Proyect.Controller;

import com.awsproyect.AWS_Proyect.Models.Alumno;
import com.awsproyect.AWS_Proyect.Models.Request.AlumnoDTO;
import com.awsproyect.AWS_Proyect.Models.Request.UpdateAlumnosRequestDTO;
import com.awsproyect.AWS_Proyect.Service.IAlumnosService;
import com.awsproyect.AWS_Proyect.Service.Implementation.AlumnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final IAlumnosService iAlumnosService;


    @GetMapping
    public ResponseEntity<List<Alumno>> getAlumnos(){
        var response = iAlumnosService.getAlumnos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumnosById(@PathVariable Long id){
        var response = iAlumnosService.getAlumnoById(id);
        return response.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AlumnoDTO> postAlumno (@Valid @RequestBody AlumnoDTO alumno){
       Long id = iAlumnosService.createAlumno(alumno);
        return ResponseEntity.status(201).body(alumno);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id){
        boolean eliminado = iAlumnosService.deleteAlumno(id);
        if(eliminado){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> putAlumno(@PathVariable Long id, @Valid @RequestBody UpdateAlumnosRequestDTO alumno){
        return iAlumnosService.updateAlumno(id, alumno)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
