package com.intercorp.challenge.service;

import com.intercorp.challenge.model.IcClient;
import com.intercorp.challenge.model.ValidData;
import org.springframework.stereotype.Component;

@Component
public interface ModelValidationService {
    ValidData validateClient(IcClient client);
}
