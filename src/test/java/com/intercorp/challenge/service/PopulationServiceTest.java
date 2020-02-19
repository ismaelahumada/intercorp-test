package com.intercorp.challenge.service;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PopulationServiceTest {
    public static final int LIFE_EXPECTANCY = 40;
    PopulationService serviceAtTest = new PopulationService(LIFE_EXPECTANCY);

    @Test
    void calculateDateOfProbableDeath() {
        //Prepare
        int year = LocalDate.now().getYear();
        int month = 4;
        int dayOfMonth = 28;
        //Test
        LocalDate dateOfProbableDeath = serviceAtTest.calculateDateOfProbableDeath(LocalDate.of(year, month, dayOfMonth));
        //Verify
        assertEquals(year + 40, dateOfProbableDeath.getYear(), "Probable Death doesn't have the right year");
        assertEquals(Month.of(month), dateOfProbableDeath.getMonth(), "Probable Death doesn't have the right month");
        assertEquals(dayOfMonth, dateOfProbableDeath.getDayOfMonth(), "Probable Death doesn't have the right dayOfMonth");
    }
}