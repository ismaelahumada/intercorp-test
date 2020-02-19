package com.intercorp.challenge.persistence.service;

import com.intercorp.challenge.model.ClientKpi;
import com.intercorp.challenge.model.IcClient;
import com.intercorp.challenge.persistence.dao.IClientDao;
import com.intercorp.challenge.persistence.entity.Client;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ClientDBService {
    private IClientDao<Client> clientDao;
    private DozerBeanMapper dozerBeanMapper;

    public ClientDBService(@Autowired IClientDao<Client> clientDao, @Autowired DozerBeanMapper dozerBeanMapper) {
        this.clientDao = clientDao;
        this.dozerBeanMapper = dozerBeanMapper;
    }

    public IcClient save(IcClient client) throws SQLIntegrityConstraintViolationException {
        Client testClient = dozerBeanMapper.map(client, Client.class);

        Optional<Client> clientOptional = clientDao.save(testClient);
        if (clientOptional.isPresent()) {
            return dozerBeanMapper.map(clientOptional.get(), IcClient.class);
        }
        throw new SQLIntegrityConstraintViolationException("Could not perform transaction");
    }

    public List<IcClient> listAll() {
        List<Client> clientList = clientDao.getAll();
        List<IcClient> icClientList = new ArrayList<>();
        clientList.forEach(client -> icClientList.add(dozerBeanMapper.map(client, IcClient.class)));
        return icClientList;
    }

    @Transactional
    public ClientKpi getClientKPI() {
        Double ageAverage = clientDao.getAgeAverage();
        Double standardDeviation = getStandardDeviation(clientDao.getAllAges(), ageAverage);

        return new ClientKpi(ageAverage, standardDeviation);
    }

    private Double getStandardDeviation(List<Integer> ageList, Double ageAverage) {
        double sumOfSqsOfDiffBtwAgeAndAvg = ageList.stream().mapToDouble(age ->
                (age - ageAverage) * (age - ageAverage)
        ).sum();
        return Math.sqrt(sumOfSqsOfDiffBtwAgeAndAvg / ageList.size());
    }
}
