package com.awsproyect.AWS_Proyect.Controller;

import com.awsproyect.AWS_Proyect.Models.Alumno;
import com.awsproyect.AWS_Proyect.Models.Request.AlumnoDTO;
import com.awsproyect.AWS_Proyect.Models.Request.UpdateAlumnosRequestDTO;
import com.awsproyect.AWS_Proyect.Models.Response.AlumnoResponseDTO;
import com.awsproyect.AWS_Proyect.Service.IAlumnosService;
import com.awsproyect.AWS_Proyect.Service.IS3Service;
import com.awsproyect.AWS_Proyect.Service.Implementation.AlumnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
@RequiredArgsConstructor
public class AlumnoController {

    private final IAlumnosService iAlumnosService;

    private final IS3Service iS3Service;




    @GetMapping
    public ResponseEntity<List<Alumno>> getAlumnos(){
        var response = iAlumnosService.getAlumnos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumnosById(@PathVariable Long id){
        var response = iAlumnosService.getAlumnoById(id);
        return response.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AlumnoResponseDTO> postAlumno (@Valid @RequestBody AlumnoDTO alumno){
       Long id = iAlumnosService.createAlumno(alumno);

        if(id != null && id > 0){
            var response = new AlumnoResponseDTO(id);
            return ResponseEntity.status(201).body(response);
        }
        return ResponseEntity.badRequest().build();
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

    @PostMapping("{id}/fotoPerfil")
    public  ResponseEntity<String> uploandfotoPerfile(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        try{
            iAlumnosService.uploandfotoPerfile(id, file);
            return ResponseEntity.ok().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("{id}/email")
    public ResponseEntity<String> sendEmail(@PathVariable Long id ) {
        boolean enviado = iAlumnosService.sendEmail(id);
        if(enviado){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.badRequest().build();
        }

    }

}
