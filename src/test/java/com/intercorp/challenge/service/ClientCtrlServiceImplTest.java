package com.intercorp.challenge.service;

import com.intercorp.challenge.model.IcClient;
import com.intercorp.challenge.model.ValidData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ClientCtrlServiceImplTest {
    @Mock
    private ModelValidationService clientValidator;
    @InjectMocks
    private ClientCtrlServiceImpl serviceAtTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calculateKpi() {
    }

    @Test
    void validateModel_invokesClientValidator() {
        //Prepare
        IcClient icClient = new IcClient();
        //Test
        ValidData result = serviceAtTest.validateModel(icClient);
        //Validate
        verify(clientValidator, times(1)).validateClient(icClient);
    }
}