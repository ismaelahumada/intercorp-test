package com.intercorp.challenge.service;

import com.intercorp.challenge.model.ClientKpi;
import com.intercorp.challenge.model.IcClient;
import com.intercorp.challenge.model.ValidData;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Component
public interface ClientCtrlService {
    ClientKpi calculateKpi(boolean isPopulation);

    ValidData validateModel(IcClient client);

    IcClient create(IcClient client) throws SQLIntegrityConstraintViolationException;

    List<IcClient> listClients();
}
