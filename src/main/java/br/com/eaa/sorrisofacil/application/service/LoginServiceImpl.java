package br.com.eaa.sorrisofacil.application.service;

import br.com.eaa.sorrisofacil.adapters.dto.login.LoginResponseDTO;
import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.LoginException;
import br.com.eaa.sorrisofacil.application.domain.Administrator;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.User;
import br.com.eaa.sorrisofacil.application.port.AdministratorRepositoryPort;
import br.com.eaa.sorrisofacil.application.port.DentistRepositoryPort;
import br.com.eaa.sorrisofacil.application.port.LoginServicePort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginServicePort {
    @Autowired
    private AdministratorRepositoryPort administratorRepositoryPort;

    @Autowired
    private DentistRepositoryPort dentistRepositoryPort;

    private static final String SECRET = "*--*/4265a3-*3*/e9s+w9/3e*-";

    private static final long EXPIRATION_TIME = 172800000;

    private static final String TOKEN_PREFIX = "Bearer";

    @Override
    public boolean validToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject() != null;
    }

    @Override
    public LoginResponseDTO login(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        Administrator admin = administratorRepositoryPort.getAdministratorByEmailAndPassword(email,password);
        Dentist dentist = dentistRepositoryPort.getDentistByEmailAndPassword(email,password);
        if(admin != null){
            return new LoginResponseDTO(generateToken(admin.getEmail()),true);
        }else if(dentist != null){
            return new LoginResponseDTO(dentist.getEmail(),false);
        }else{
            throw new LoginException("Error with login");
        }
    }

    @Override
    public User getUser(String token) {
        String user = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody().getSubject();
        if(user != null){
            Administrator administrator = administratorRepositoryPort.getAdministratorByEmail(user);
            Dentist dentist = dentistRepositoryPort.getDentistByEmail(user);
            if(administrator != null){
                return new User(administrator.getId(),administrator.getName(),administrator.getPassword(),administrator.getEmail());
            }else if(dentist != null){
                return new User(dentist.getId(),dentist.getName(),dentist.getPassword(),dentist.getEmail());
            }else{
                throw new LoginException("Error with token please send a valid token");
            }
        }
        throw new LoginException("Error with token please send a valid token");
    }

    @Override
    public Class getRole(String token) {
        User user = getUser(token);
        if(administratorRepositoryPort.getAdministrator(user.getId()) != null){
            return Administrator.class;
        }else{
            return Dentist.class;
        }
    }

    private String generateToken(String user){
        return Jwts.builder().setSubject(user).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }
}
