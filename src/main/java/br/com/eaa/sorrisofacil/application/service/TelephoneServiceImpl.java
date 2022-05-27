package br.com.eaa.sorrisofacil.application.service;

import br.com.eaa.sorrisofacil.application.domain.Client;
import br.com.eaa.sorrisofacil.application.domain.PageInformation;
import br.com.eaa.sorrisofacil.application.domain.Telephone;
import br.com.eaa.sorrisofacil.application.port.telephone.TelephoneRepositoryPort;
import br.com.eaa.sorrisofacil.application.port.telephone.TelephoneServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class TelephoneServiceImpl implements TelephoneServicePort {

    @Autowired
    private TelephoneRepositoryPort port;
    @Override
    public Telephone getTelephone(Long id) {
        return port.getTelephone(id);
    }
    @Override
    public Telephone insertTelephone(Telephone telephone) {
        return port.insertTelephone(telephone);
    }
    @Override
    public Telephone updateTelephone(Long id, Telephone telephone) {
        return port.updateTelephone(id, telephone);
    }

    @Override
    public void removeTelephone(Long id) {
        port.removeTelephone(id);
    }

    @Override
    public Page<Telephone> getTelephones(PageInformation pageInformation, Client client) {
        return port.getTelephones(pageInformation, client);
    }
}
