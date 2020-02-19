package com.intercorp.challenge.api.controller;

import com.intercorp.challenge.api.response.IcClientKpiResponse;
import com.intercorp.challenge.api.response.IcClientListResponse;
import com.intercorp.challenge.api.response.IcClientResponse;
import com.intercorp.challenge.model.ClientKpi;
import com.intercorp.challenge.model.ErrorResponse;
import com.intercorp.challenge.model.IcClient;
import com.intercorp.challenge.model.ValidData;
import com.intercorp.challenge.service.ClientCtrlService;
import com.intercorp.challenge.service.PopulationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


@RestController @Slf4j
public class ClientController {
    @Autowired
    private ClientCtrlService clientCtrlServiceImpl;
    @Autowired
    private PopulationService populationService;

    //TODO: Verify Rest Documentation suggests that endpoints of REST Services should be the name of the resource "/client" would serve for both GET(/client/{id}) and GET(/client -> list) and POST HTTP Methods.
    @PostMapping(value = "/creacliente")
    public ResponseEntity<IcClientResponse> createClient(@RequestBody IcClient client) throws SQLIntegrityConstraintViolationException {
        ValidData validDataIcClient = clientCtrlServiceImpl.validateModel(client);
        if (validDataIcClient.isValid()) {
            IcClient savedClient = clientCtrlServiceImpl.create(client);
            IcClientResponse icClientResponse = new IcClientResponse(savedClient, HttpStatus.CREATED.getReasonPhrase());
            URI location = generateLocationUri(savedClient.getId());//TODO: This will generate incorrect GET location because of non standard naming. The naming should be fixed.
            return ResponseEntity.created(location).body(icClientResponse);
        } else {
            IcClientResponse icClientResponse = new IcClientResponse(null, validDataIcClient.getErrorMessage());
            return ResponseEntity.badRequest().body(icClientResponse);
        }
    }

    @GetMapping(value = "/kpideclientes")
    public ResponseEntity<IcClientKpiResponse> getClientsKPI() {
        ClientKpi clientKpi = clientCtrlServiceImpl.calculateKpi(true);
        return ResponseEntity.ok(new IcClientKpiResponse(clientKpi, HttpStatus.OK.getReasonPhrase()));
    }

    //TODO: Verify Rest Documentation suggests that endpoints of REST Services should be the name of the resource "/client" would serve for both GET(/client/{id}) and GET(/client -> list) and POST HTTP Methods.
    @GetMapping(value = "/listclientes")
    public ResponseEntity<IcClientListResponse> getClientList() {
        List<IcClient> icClientList = clientCtrlServiceImpl.listClients();
        icClientList.forEach(client -> client.setFallecimientoProbable(populationService.calculateDateOfProbableDeath(client.getNacimiento())));

        return ResponseEntity.ok().body(new IcClientListResponse(icClientList, HttpStatus.OK.getReasonPhrase()));
    }

    private URI generateLocationUri(long id) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity handleNotFoundException(SQLIntegrityConstraintViolationException e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
    }
}

