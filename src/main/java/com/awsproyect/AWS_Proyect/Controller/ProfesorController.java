package com.awsproyect.AWS_Proyect.Controller;

import com.awsproyect.AWS_Proyect.Models.Profesor;
import com.awsproyect.AWS_Proyect.Models.Request.ProfesorDTO;
import com.awsproyect.AWS_Proyect.Models.Request.UpdateProfesorResponseDTO;
import com.awsproyect.AWS_Proyect.Service.IProfesorService;
import com.awsproyect.AWS_Proyect.Service.Implementation.ProfesorService;
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

    private final IProfesorService iProfesorService;

    @GetMapping
    public ResponseEntity<List<Profesor>> getAllProfesores(){
        var response = iProfesorService.getProfesores();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable Long id){
       var response = iProfesorService.getProfesorById(id);
       return response.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProfesorDTO> postProfesor (@Valid @RequestBody ProfesorDTO profesor){
        Long id = iProfesorService.createProfesor(profesor);
        return ResponseEntity.status(201).body(profesor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> putProfesor (@PathVariable Long id, @Valid @RequestBody UpdateProfesorResponseDTO profesor){
        return iProfesorService.updateProfesor(id,profesor)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfesor (@PathVariable Long id){
        boolean eliminado = iProfesorService.deleteProfesor(id);
        if(eliminado){
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
}
