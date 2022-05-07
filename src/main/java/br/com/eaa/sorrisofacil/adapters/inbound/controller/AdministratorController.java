package br.com.eaa.sorrisofacil.adapters.inbound.controller;

import br.com.eaa.sorrisofacil.adapters.dto.AdministratorDTO;
import br.com.eaa.sorrisofacil.adapters.dto.AdministratorReturn;
import br.com.eaa.sorrisofacil.adapters.dto.AdministratorUpdateDTO;
import br.com.eaa.sorrisofacil.application.domain.Administrator;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.port.AdministratorServicePort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
public class AdministratorController {

    @Autowired
    private AdministratorServicePort port;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("administrators")
    public Page<AdministratorReturn> administrators(){
        return port.getAdministrators(new PageInformation(5,0)).map(x ->mapper.map(x, AdministratorReturn.class));
    }

    @GetMapping("administrators/{page}")
    public Page<AdministratorReturn> administrators(@PathVariable Integer page){
        return port.getAdministrators(new PageInformation(5,page)).map(x->mapper.map(x,AdministratorReturn.class));
    }

    @PostMapping("administrator")
    public AdministratorReturn saveAdministrator(@RequestBody @Valid AdministratorDTO dto){
        return mapper.map(port.insert(mapper.map(dto, Administrator.class)),AdministratorReturn.class);
    }

    @GetMapping("administrator/{id}")
    public AdministratorReturn getAdministrator(@PathVariable Long id){
        return mapper.map(port.getAdministrator(id),AdministratorReturn.class);
    }

    @PutMapping("administrator/{id}")
    public AdministratorReturn updateAdministrator(@RequestBody @Valid AdministratorUpdateDTO dto, @PathVariable Long id){
        return mapper.map(port.update(id,mapper.map(dto, Administrator.class)),AdministratorReturn.class);
    }

    @DeleteMapping("administrator/{id}")
    public ResponseEntity<Void> removeAdministrator(@PathVariable Long id){
        port.removeAdministrator(id);
        return ResponseEntity.ok().build();
    }
}
