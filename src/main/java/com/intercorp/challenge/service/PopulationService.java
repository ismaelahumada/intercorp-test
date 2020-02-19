package com.intercorp.challenge.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PopulationService {
    private Integer lifeExpectancy;

    public PopulationService(@Value("${generic.life.expectancy.years}") Integer lifeExpectancy) {
        this.lifeExpectancy = lifeExpectancy;
    }

    public LocalDate calculateDateOfProbableDeath(LocalDate birthDate) {
        return LocalDate.of(
                birthDate.getYear() + lifeExpectancy,
                birthDate.getMonth(),
                birthDate.getDayOfMonth());
    }
}
