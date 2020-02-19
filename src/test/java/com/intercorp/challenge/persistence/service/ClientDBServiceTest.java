package com.intercorp.challenge.persistence.service;

import com.intercorp.challenge.model.ClientKpi;
import com.intercorp.challenge.model.IcClient;
import com.intercorp.challenge.persistence.dao.IClientDao;
import com.intercorp.challenge.persistence.entity.Client;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientDBServiceTest {
    public static final String NAME = "Mark";
    public static final String LAST_NAME = "Twain";
    public static final int MONTH = 4;
    public static final int DAY_OF_MONTH = 20;
    @Spy
    private DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
    @Mock
    private IClientDao<Client> clientDao;
    @InjectMocks
    private ClientDBService serviceAtTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        dozerBeanMapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
    }

    @Test
    void save_verifyDaoIsInvoked_modelIsCorrectlyMapped() throws SQLIntegrityConstraintViolationException {
        //Prepare
        long id = 10L;
        int edad = 18;
        Client savedClient = generateClient(id, edad);
        when(clientDao.save(any(Client.class))).thenReturn(Optional.of(savedClient));

        //Test
        IcClient returnedIcClient = serviceAtTest.save(new IcClient());

        //Verify
        verify(clientDao, times(1)).save(any(Client.class));
        assertEquals(savedClient.getAge(), returnedIcClient.getEdad(), "Ages are not the same");
        assertEquals(savedClient.getBirthDate(), returnedIcClient.getNacimiento(), "BirthDates are not the same");
        assertEquals(savedClient.getLastName(), returnedIcClient.getApellido(), "LastNames are not the same");
        assertEquals(id, returnedIcClient.getId(), "ids are not the same");
    }

    @Test
    void listAll_verifyDaoIsInvoked_returnedValuesMatch() {
        //Prepare
        long firstId = 10L;
        int firstAge = 18;
        Client firstListedClient = generateClient(firstId, firstAge);
        long secondId = 15L;
        int secondAge = 20;
        Client secondListedClient = generateClient(secondId, secondAge);
        List<Client> clientList = Arrays.asList(firstListedClient, secondListedClient);
        when(clientDao.getAll()).thenReturn(clientList);

        //Test
        List<IcClient> icClientList = serviceAtTest.listAll();

        //Verify
        verify(clientDao, times(1)).getAll();

        IcClient firstReturnedIcClient = icClientList.get(0);
        IcClient secondReturnedIcClient = icClientList.get(1);

        assertEquals(firstId, firstReturnedIcClient.getId(), "Ids do not match");
        assertEquals(firstAge, firstReturnedIcClient.getEdad(), "Ages do not match");
        assertEquals(NAME, firstReturnedIcClient.getNombre(), "Names do not match");
        assertEquals(LAST_NAME, firstReturnedIcClient.getApellido(), "LastNames do not match");

        assertEquals(secondId, secondReturnedIcClient.getId(), "Ids do not match");
        assertEquals(secondAge, secondReturnedIcClient.getEdad(), "Ages do not match");
        assertEquals(NAME, secondReturnedIcClient.getNombre(), "Names do not match");
        assertEquals(LAST_NAME, secondReturnedIcClient.getApellido(), "LastNames do not match");
    }

    @Test
    void getClientKPI() {
        double ageAverage = 10.5d;
        when(clientDao.getAgeAverage()).thenReturn(ageAverage);
        when(clientDao.getAllAges()).thenReturn(Arrays.asList(10, 10, 11, 11));
        ClientKpi clientKPI = serviceAtTest.getClientKPI();
        assertNotNull(clientKPI);
        assertEquals(ageAverage, clientKPI.getAgeAverage());
        double expectedStdDev = 0.5;
        assertEquals(expectedStdDev, clientKPI.getAgeStandardDeviation());
    }

    private Client generateClient(long id, int edad) {
        Client savedClient = new Client();
        savedClient.setName(NAME);
        savedClient.setLastName(LAST_NAME);
        savedClient.setId(id);
        int birthYear = LocalDate.now().getYear() - edad;
        LocalDate birthDate = LocalDate.of(birthYear, MONTH, DAY_OF_MONTH);
        savedClient.setBirthDate(birthDate);
        savedClient.setAge(edad);
        return savedClient;
    }
}