package br.com.eaa.sorrisofacil.adapters.inbound.controller;

import br.com.eaa.sorrisofacil.adapters.dto.dentist.DentistDTO;
import br.com.eaa.sorrisofacil.adapters.dto.dentist.DentistReturn;
import br.com.eaa.sorrisofacil.adapters.dto.dentist.DentistUpdateDTO;
import br.com.eaa.sorrisofacil.adapters.inbound.security.SecurityUtil;
import br.com.eaa.sorrisofacil.adapters.outbound.exceptions.LoginException;
import br.com.eaa.sorrisofacil.application.domain.Dentist;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.DentistServicePort;
import br.com.eaa.sorrisofacil.application.port.LoginServicePort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


@RestController
public class DentistController {
    @Autowired
    private DentistServicePort port;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SecurityUtil util;

    @Autowired
    private LoginServicePort loginServicePort;

    @GetMapping("dentists")
    public Page<DentistReturn> dentists(HttpServletRequest request){
        util.adminAuthorized(request);
        return port.getDentists(new PageInformation(5,0)).map(x->mapper.map(x,DentistReturn.class));
    }

    @GetMapping("dentists/{page}")
    public Page<DentistReturn> dentists(HttpServletRequest request,@PathVariable Integer page){
        util.adminAuthorized(request);
        return port.getDentists(new PageInformation(5,page)).map(x->mapper.map(x,DentistReturn.class));
    }

    @PostMapping("dentist")
    public DentistReturn saveDentist(HttpServletRequest request,@RequestBody @Valid DentistDTO dentist) throws NoSuchAlgorithmException, InvalidKeySpecException {
        util.adminAuthorized(request);
        return mapper.map(port.insert(mapper.map(dentist, Dentist.class)),DentistReturn.class);
    }

    @GetMapping("dentist/{id}")
    public DentistReturn getDentist(HttpServletRequest request,@PathVariable Long id){
        if(util.dentistAuthorized(request, true)){
            if(!loginServicePort.getUser(request.getHeader("Authorization")).getId().equals(id)){
                throw new LoginException("Unauthorized");
            }
        }
        return mapper.map(port.getDentist(id),DentistReturn.class);
    }

    @PutMapping("dentist/{id}")
    public DentistReturn updateDentist(HttpServletRequest request,@PathVariable Long id, @RequestBody @Valid DentistUpdateDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if(util.dentistAuthorized(request, true)){
            if(!loginServicePort.getUser(request.getHeader("Authorization")).getId().equals(id)){
                throw new LoginException("Unauthorized");
            }
        }
        return mapper.map(port.update(id,mapper.map(dto,Dentist.class)),DentistReturn.class);
    }

    @DeleteMapping("dentist/{id}")
    public ResponseEntity<Void> deleteDentist(HttpServletRequest request, @PathVariable Long id){
        if(util.dentistAuthorized(request, true)){
            if(!loginServicePort.getUser(request.getHeader("Authorization")).getId().equals(id)){
                throw new LoginException("Unauthorized");
            }
        }
        port.removeDentist(id);
        return ResponseEntity.ok().build();
    }
}
