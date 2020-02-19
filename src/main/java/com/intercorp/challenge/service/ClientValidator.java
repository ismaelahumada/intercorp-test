package com.intercorp.challenge.service;

import com.intercorp.challenge.model.IcClient;
import com.intercorp.challenge.model.ValidData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ClientValidator implements ModelValidationService {

    public static final String AGE_DOES_NOT_MATCH_BIRTH_DATE = "Age does not match Birth Date";
    public static final String AGE_IS_INVALID_SHOULD_BE_10_130 = "Age is invalid (should be 10~130)";
    public static final String LAST_NAME_CANNOT_BE_NULL_NOR_EMPTY = "Last Name cannot be null nor empty";
    public static final String NAME_CANNOT_BE_NULL_NOR_EMPTY = "Name cannot be null nor empty";

    @Override
    public ValidData validateClient(IcClient client) {
        ValidData validDataIcClient = new ValidData();
        validDataIcClient.setValid(
                client != null
                        && isNameValid(client.getNombre(), validDataIcClient)
                        & isLastNameValid(client.getApellido(), validDataIcClient)
                        & isAgeAndBirthDateValid(client.getNacimiento(), client.getEdad(), validDataIcClient));
        return validDataIcClient;
    }

    private boolean isAgeAndBirthDateValid(LocalDate birthDate, Integer age, ValidData validDataIcClient) {
        return isAgeValid(age, validDataIcClient) & doesBirthDateMatchWithAge(birthDate, age, validDataIcClient);
    }

    private boolean doesBirthDateMatchWithAge(LocalDate birthDate, Integer age, ValidData validDataIcClient) {
        int thisYear = LocalDate.now().getYear();
        boolean matchesAge = birthDate != null
                && age != null
                && (birthDate.getYear() + age == thisYear
                || birthDate.getYear() + age == thisYear - 1);
        validDataIcClient.setErrorMessage(matchesAge ? StringUtils.EMPTY : AGE_DOES_NOT_MATCH_BIRTH_DATE);
        return matchesAge;
        //TODO: this validation should take exact date into account, left like this for interview purposes
    }

    private boolean isAgeValid(Integer age, ValidData validDataIcClient) {
        boolean isAgeValid = age != null
                && age >= 10
                && age < 130;
        validDataIcClient.setErrorMessage(isAgeValid ? StringUtils.EMPTY : AGE_IS_INVALID_SHOULD_BE_10_130);
        return isAgeValid;
        //TODO: this parameters should be configurable, left like this for interview purposes
    }

    private boolean isLastNameValid(String lastName, ValidData validDataIcClient) {
        boolean isLastNameValid = StringUtils.isNotEmpty(lastName);
        validDataIcClient.setErrorMessage(isLastNameValid ? StringUtils.EMPTY : LAST_NAME_CANNOT_BE_NULL_NOR_EMPTY);
        return isLastNameValid;
    }

    private boolean isNameValid(String name, ValidData validDataIcClient) {
        boolean isNameValid = StringUtils.isNotEmpty(name);
        validDataIcClient.setErrorMessage(isNameValid ? StringUtils.EMPTY : NAME_CANNOT_BE_NULL_NOR_EMPTY);
        return isNameValid;
    }

}
