package com.awsproyect.AWS_Proyect.Controller;

import com.awsproyect.AWS_Proyect.Models.Profesor;
import com.awsproyect.AWS_Proyect.Service.ProfesorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
@RequiredArgsConstructor
public class ProfesorController {
    @Autowired
    private ProfesorService profesorService;

    @GetMapping
    public ResponseEntity<List<Profesor>> getAllProfesores(){
        return ResponseEntity.ok(profesorService.getProfesoresList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable Long id){
       return profesorService.getProfesorById(id)
                .map(profesor -> ResponseEntity.ok(profesor))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Profesor> postProfesor (@Valid @RequestBody Profesor profesor){
        return ResponseEntity.status(201).body(profesorService.createProfesor(profesor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> putProfesor (@PathVariable Long id, @Valid @RequestBody Profesor profesor){
        return profesorService.updateProfesor(id, profesor)
                .map(nuevoProfesor-> ResponseEntity.ok(nuevoProfesor))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor (@PathVariable Long id){
        boolean eliminado = profesorService.deleteProfesor(id);
        if(eliminado){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
