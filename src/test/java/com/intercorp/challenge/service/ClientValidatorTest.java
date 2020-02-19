package com.intercorp.challenge.service;

import com.intercorp.challenge.model.IcClient;
import com.intercorp.challenge.model.ValidData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static com.intercorp.challenge.service.ClientValidator.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClientValidatorTest {
    private ClientValidator clientValidator;

    @BeforeEach
    void setUp() {
        clientValidator = new ClientValidator();
    }

    @Test
    void validateClient_withValidValues_thenReturnTrue() {
        //Prepare
        IcClient icClient = new IcClient();
        setAllBasicValues(icClient);
        //Test
        ValidData result = clientValidator.validateClient(icClient);
        //Validate
        assertTrue(result.isValid());
    }

    @Test
    void validateClient_withAllNullValues_thenReturnFalse() {
        //Prepare
        IcClient icClient = new IcClient();
        //Test
        ValidData result = clientValidator.validateClient(icClient);
        //Validate
        assertFalse(result.isValid());
        assertTrue(result.getErrorMessage().contains(AGE_DOES_NOT_MATCH_BIRTH_DATE));
        assertTrue(result.getErrorMessage().contains(AGE_IS_INVALID_SHOULD_BE_10_130));
        assertTrue(result.getErrorMessage().contains(NAME_CANNOT_BE_NULL_NOR_EMPTY));
        assertTrue(result.getErrorMessage().contains(LAST_NAME_CANNOT_BE_NULL_NOR_EMPTY));
    }

    @Test
    void validateClient_withNullBirthDate_thenReturnFalse() {
        //Prepare
        IcClient icClient = new IcClient();
        setAllBasicValues(icClient);
        icClient.setNacimiento(null);
        //Test
        ValidData result = clientValidator.validateClient(icClient);
        //Validate
        assertFalse(result.isValid());
        assertTrue(result.getErrorMessage().contains(AGE_DOES_NOT_MATCH_BIRTH_DATE));
    }

    @Test
    void validateClient_withNonMatchingBirthDate_thenReturnFalse() {
        //Prepare
        IcClient icClient = new IcClient();
        setAllBasicValues(icClient);
        icClient.setEdad(21);
        //Test
        ValidData result = clientValidator.validateClient(icClient);
        //Validate
        assertFalse(result.isValid());
        assertTrue(result.getErrorMessage().contains(AGE_DOES_NOT_MATCH_BIRTH_DATE));
    }

    @Test
    void validateClient_withNullName_thenReturnFalse() {
        //Prepare
        IcClient icClient = new IcClient();
        setAllBasicValues(icClient);
        icClient.setNombre(null);
        //Test
        ValidData result = clientValidator.validateClient(icClient);
        //Validate
        assertFalse(result.isValid());
        assertTrue(result.getErrorMessage().contains(NAME_CANNOT_BE_NULL_NOR_EMPTY));
    }

    @Test
    void validateClient_withEmptyName_thenReturnFalse() {
        //Prepare
        IcClient icClient = new IcClient();
        setAllBasicValues(icClient);
        icClient.setNombre("");
        //Test
        ValidData result = clientValidator.validateClient(icClient);
        //Validate
        assertFalse(result.isValid());
        assertTrue(result.getErrorMessage().contains(NAME_CANNOT_BE_NULL_NOR_EMPTY));
    }

    @Test
    void validateClient_withNullLastName_thenReturnFalse() {
        //Prepare
        IcClient icClient = new IcClient();
        setAllBasicValues(icClient);
        icClient.setApellido(null);
        //Test
        ValidData result = clientValidator.validateClient(icClient);
        //Validate
        assertFalse(result.isValid());
        assertTrue(result.getErrorMessage().contains(LAST_NAME_CANNOT_BE_NULL_NOR_EMPTY));
    }

    @Test
    void validateClient_withEmptyLastName_thenReturnFalse() {
        //Prepare
        IcClient icClient = new IcClient();
        setAllBasicValues(icClient);
        icClient.setApellido("");
        //Test
        ValidData result = clientValidator.validateClient(icClient);
        //Validate
        assertFalse(result.isValid());
        assertTrue(result.getErrorMessage().contains(LAST_NAME_CANNOT_BE_NULL_NOR_EMPTY));
    }

    @Test
    void validateClient_withInvalidAge_thenReturnFalse() {
        //Prepare
        IcClient icClient = new IcClient();
        setAllBasicValues(icClient);
        icClient.setEdad(0);
        //Test
        ValidData result = clientValidator.validateClient(icClient);
        //Validate
        assertFalse(result.isValid());
        assertTrue(result.getErrorMessage().contains(AGE_IS_INVALID_SHOULD_BE_10_130));
    }

    @Test
    void validateClient_withNullAge_thenReturnFalse() {
        //Prepare
        IcClient icClient = new IcClient();
        setAllBasicValues(icClient);
        icClient.setEdad(null);
        //Test
        ValidData result = clientValidator.validateClient(icClient);
        //Validate
        assertFalse(result.isValid());
        assertTrue(result.getErrorMessage().contains(AGE_IS_INVALID_SHOULD_BE_10_130));
    }

    private void setAllBasicValues(IcClient icClient) {
        icClient.setNombre("Mariano");
        icClient.setApellido("Perez");
        int age = 19;
        icClient.setEdad(age);
        icClient.setNacimiento(LocalDate.of(LocalDate.now().getYear() - age, 4, 30));
    }
}