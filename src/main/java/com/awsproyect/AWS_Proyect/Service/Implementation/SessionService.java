package com.awsproyect.AWS_Proyect.Service.Implementation;

import com.awsproyect.AWS_Proyect.Exception.NotFoundException;
import com.awsproyect.AWS_Proyect.Models.AlumnoSesion;
import com.awsproyect.AWS_Proyect.Repository.SessionRepository;
import com.awsproyect.AWS_Proyect.Service.ISessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class SessionService implements ISessionService {

    private final SessionRepository sessionRepository;
    private final AlumnoService alumnoService;

    public String login (Long id, String password){
        var alumnoSegun = alumnoService.getAlumnoById(id);
        if(alumnoSegun.isEmpty()){
            throw  new NotFoundException("Alumno no encontrado");
        }

        var alumno = alumnoSegun.get();
        if(!password.equals(alumno.getPassword())){
            throw new RuntimeException("contraseña invalida");
        }

        String sessionString = generateRandomString(128);

        var session = AlumnoSesion.builder()
                .fecha(Instant.now())
                .alumnoId(alumno.getId())
                .activo(true)
                .sessionString(sessionString)
                .build();
        sessionRepository.save(session);
        return sessionString;
    }

    public boolean verifySession(String sessionString){
        var sessionSegun = sessionRepository.findSessionString(sessionString);
        if(sessionSegun.isEmpty()){
            return false;
        }
        var session = sessionSegun.get();
        if(session.isActivo()){
            return true;
        }
        return  false;
    }

    public  boolean  logoutSession(String sessionString){
        var sessionActive = sessionRepository.findSessionString(sessionString);
        if(sessionActive.isEmpty()){

            return false;
        }
        AlumnoSesion  session = sessionActive.get();
        session.setActivo(false);
        sessionRepository.save(session);
        return true;

    }

    private String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
