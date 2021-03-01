package com.carmaxx.rental.api;

import com.carmaxx.rental.dao.CarDAO;
import com.carmaxx.rental.model.Car;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerIntegrationTests {

    @MockBean
    private CarDAO carDAO;

    @MockBean
    private CarController carController;

    private List<Car> carList;
    private ObjectMapper objectMapper;

    @Autowired
    TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        carList = new ArrayList<>();
        carList.add(new Car(UUID.randomUUID(), 'Y', "DBS", "Aston Martin", 0));
        carList.add(new Car(UUID.randomUUID(), 'Y', "Aventador", "Lamborghini", 0));
    }

    @Test
    public void  testGetAllCars() throws Exception {
        // given
        given(carController.getAllCars()).willReturn(carList);

        // when
        ResponseEntity<Car[]> carResponse = restTemplate.getForEntity("/api/v1/car/", Car[].class);

        // then
        assertThat(carResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testGetExistingCar() throws Exception {
        UUID existingCarId = carList.get(1).getId();
        given(carController.getCar(existingCarId)).willReturn(carList.get(1));

        ResponseEntity<Car> carResponse = restTemplate.getForEntity("/api/v1/car/" + existingCarId, Car.class);

        assertThat(carResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(carResponse.getBody().getManufacturer()).isEqualTo("Lamborghini");
    }

    @Test
    public void testAddNewCar() throws Exception {
        // given data
        Car newCar = new Car(UUID.randomUUID(), 'N', "GT", "Ford", 1);

        // when
        ResponseEntity<Car> carResponse = restTemplate.postForEntity("/api/v1/car/", newCar, Car.class);

        // then
        assertThat(carResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    
}
