package br.com.eaa.sorrisofacil.adapters.inbound.controller;

import br.com.eaa.sorrisofacil.adapters.dto.administrator.AdministratorDTO;
import br.com.eaa.sorrisofacil.adapters.dto.administrator.AdministratorReturn;
import br.com.eaa.sorrisofacil.adapters.dto.administrator.AdministratorUpdateDTO;
import br.com.eaa.sorrisofacil.adapters.inbound.security.SecurityUtil;
import br.com.eaa.sorrisofacil.application.domain.Administrator;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.AdministratorServicePort;
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
public class AdministratorController {

    @Autowired
    private AdministratorServicePort port;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SecurityUtil util;

    @GetMapping("administrators")
    public Page<AdministratorReturn> administrators(HttpServletRequest request){
        util.adminAuthorized(request);
        return port.getAdministrators(new PageInformation(5,0)).map(x ->mapper.map(x, AdministratorReturn.class));
    }

    @GetMapping("administrators/{page}")
    public Page<AdministratorReturn> administrators(HttpServletRequest request,@PathVariable Integer page){
        util.adminAuthorized(request);
        return port.getAdministrators(new PageInformation(5,page)).map(x->mapper.map(x,AdministratorReturn.class));
    }

    @PostMapping("administrator")
    public AdministratorReturn saveAdministrator(HttpServletRequest request,@RequestBody @Valid AdministratorDTO dto) throws NoSuchAlgorithmException, InvalidKeySpecException {
        util.adminAuthorized(request);
        return mapper.map(port.insert(mapper.map(dto, Administrator.class)),AdministratorReturn.class);
    }

    @GetMapping("administrator/{id}")
    public AdministratorReturn getAdministrator(HttpServletRequest request,@PathVariable Long id){
        util.adminAuthorized(request);
        return mapper.map(port.getAdministrator(id),AdministratorReturn.class);
    }

    @PutMapping("administrator/{id}")
    public AdministratorReturn updateAdministrator(HttpServletRequest request,@RequestBody @Valid AdministratorUpdateDTO dto, @PathVariable Long id) throws NoSuchAlgorithmException, InvalidKeySpecException {
        util.adminAuthorized(request);
        return mapper.map(port.update(id,mapper.map(dto, Administrator.class)),AdministratorReturn.class);
    }

    @DeleteMapping("administrator/{id}")
    public ResponseEntity<Void> removeAdministrator(HttpServletRequest request,@PathVariable Long id){
        util.adminAuthorized(request);
        port.removeAdministrator(id);
        return ResponseEntity.ok().build();
    }


}
