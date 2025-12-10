package com.awsproyect.AWS_Proyect.Controller;

import com.awsproyect.AWS_Proyect.Exception.NotFoundException;
import com.awsproyect.AWS_Proyect.Models.Alumno;
import com.awsproyect.AWS_Proyect.Models.Request.AlumnoDTO;
import com.awsproyect.AWS_Proyect.Models.Request.LoginRequest;
import com.awsproyect.AWS_Proyect.Models.Request.UpdateAlumnosRequestDTO;
import com.awsproyect.AWS_Proyect.Models.Request.VerifyRequestLoginDTO;
import com.awsproyect.AWS_Proyect.Models.Response.AlumnoResponseDTO;
import com.awsproyect.AWS_Proyect.Models.Response.LoginResponse;
import com.awsproyect.AWS_Proyect.Models.Response.UploandPhotoDTO;
import com.awsproyect.AWS_Proyect.Service.IAlumnosService;
import com.awsproyect.AWS_Proyect.Service.IS3Service;
import com.awsproyect.AWS_Proyect.Service.ISessionService;
import com.awsproyect.AWS_Proyect.Service.Implementation.AlumnoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    private final ISessionService iSessionService;




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
        return ResponseEntity.notFound().build();
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
    public  ResponseEntity<UploandPhotoDTO> uploandfotoPerfile(@PathVariable Long id, @RequestParam("foto") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        try{
            String fotoPerfil = iAlumnosService.uploandfotoPerfile(id, file);
            var response = new UploandPhotoDTO(fotoPerfil);
            return ResponseEntity.ok(response);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("{id}/email")
    public ResponseEntity<String> sendEmail(@PathVariable Long id ) {
        boolean enviado = iAlumnosService.sendEmail(id);
        if(enviado){
            return ResponseEntity.status(200).contentType(MediaType.APPLICATION_JSON)
                    .body("{\"message \": \"Email enviado\"}");
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("{id}/session/login")
    public ResponseEntity<LoginResponse> login(@PathVariable Long id, @Valid @RequestBody LoginRequest request){
        try {
            String sessionString = iSessionService.login(id, request.password());
            var response = new LoginResponse(sessionString);
            return ResponseEntity.ok(response);
        }catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("{id}/session/verify")
    public ResponseEntity<String> verifySession(@PathVariable Long id, @Valid @RequestBody VerifyRequestLoginDTO request){
        try {
            boolean sessionString = iSessionService.verifySession(request.sessionString());
            if(sessionString){
                return ResponseEntity.status(200)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body("{\"message \": \"Session verificado\"}");
            }else{
                return ResponseEntity.badRequest().build();

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

}
