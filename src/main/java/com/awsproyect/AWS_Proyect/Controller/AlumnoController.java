package com.awsproyect.AWS_Proyect.Controller;

import com.awsproyect.AWS_Proyect.Models.Alumno;
import com.awsproyect.AWS_Proyect.Service.AlumnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
@RequiredArgsConstructor
public class AlumnoController {
    @Autowired
    private AlumnoService alumnoService;

    @GetMapping
    public ResponseEntity<List<Alumno>> getAlumnos(){
        return ResponseEntity.ok(alumnoService.getAlumnosList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumnosById(@PathVariable Long id){

        return alumnoService.getAlumnoById(id)
                .map(alumnoEncontrado -> ResponseEntity.ok(alumnoEncontrado))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Alumno> postAlumno (@Valid @RequestBody Alumno alumno){
        return ResponseEntity.status(201).body(alumnoService.createAlumno(alumno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Long id){
        boolean eliminado = alumnoService.deleteAlumno(id);
        if(eliminado){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> putAlumno(@PathVariable Long id, @Valid @RequestBody Alumno alumno){
        return alumnoService.updateAlumno(id, alumno)
                .map(alumnoActualizado -> ResponseEntity.ok(alumnoActualizado))
                .orElse(ResponseEntity.notFound().build());
    }

}
