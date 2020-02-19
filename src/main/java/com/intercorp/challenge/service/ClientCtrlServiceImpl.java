package com.intercorp.challenge.service;

import com.intercorp.challenge.model.ClientKpi;
import com.intercorp.challenge.model.IcClient;
import com.intercorp.challenge.model.ValidData;
import com.intercorp.challenge.persistence.service.ClientDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Component
public class ClientCtrlServiceImpl implements ClientCtrlService {
    private ModelValidationService clientValidator;
    private ClientDBService clientDBService;

    public ClientCtrlServiceImpl(@Autowired ModelValidationService clientValidator, @Autowired ClientDBService clientDBService) {
        this.clientValidator = clientValidator;
        this.clientDBService = clientDBService;
    }

    @Override
    public ClientKpi calculateKpi(boolean isPopulation) {
        return clientDBService.getClientKPI();
    }

    @Override
    public ValidData validateModel(IcClient client) {
        return clientValidator.validateClient(client);
    }

    @Override
    public IcClient create(IcClient client) throws SQLIntegrityConstraintViolationException {
        return clientDBService.save(client);
    }

    @Override
    public List<IcClient> listClients() {
        return clientDBService.listAll();
    }
}
