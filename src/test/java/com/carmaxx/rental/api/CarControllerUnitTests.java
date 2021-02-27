package com.carmaxx.rental.api;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.carmaxx.rental.model.Car;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerUnitTests {
    
    @Autowired
    private MockMvc mockMvc;                // REST request and response

    @MockBean
    private CarController carController;

    @Autowired
    private ObjectMapper objectMapper;      // for dealing with JSON data

    private List<Car> carList;

    String endpoint;

    @BeforeEach
    void setUp() {
        endpoint = "/api/v1/car/";
        carList = new ArrayList<>();

        carList.add(new Car(UUID.randomUUID(), "Toyota Supra"));
        carList.add(new Car(UUID.randomUUID(), "Mitsubishi Lancer Evolution VIII"));
        carList.add(new Car(UUID.randomUUID(), "Dodge Viper"));
        carList.add(new Car(UUID.randomUUID(), "Chevrolet Corvette"));
    }
    
    @Test
    public void testGetAllCars() throws Exception {
        Mockito.when(carController.getAllCars()).thenReturn(carList);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                                                                .andExpect(status()
                                                                .isOk())
                                                            .andReturn();

        String receivedContent = mvcResult.getResponse().getContentAsString();
        String expectedContent = objectMapper.writeValueAsString(carList);

        assertThat(receivedContent).isEqualTo(expectedContent);
    }

    @Test
    public void testGetCar() throws Exception {
        UUID existingCarID = carList.get(1).getId();
        endpoint = endpoint + existingCarID;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(endpoint))
                                                                .andReturn();

        int receivedStatus = mvcResult.getResponse().getStatus();
        assertThat(receivedStatus).isEqualTo(200);
    }
    

    @Test
    public void testAddCar() throws Exception {
        Car newCar = new Car(UUID.randomUUID(), "Chevrolet Cobalt SS");

        Mockito.doNothing().when(carController).addCar(newCar);

        mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
                                              .contentType("application/json")
                                              .content(objectMapper.writeValueAsString(newCar)))
                                            .andExpect(status().isOk());
    }

    @Test
    public void testUpdateCar() throws Exception {
        Car updateCar = new Car(carList.get(3).getId(), "Plymouth Road Runner");
        endpoint = endpoint + carList.get(3).getId();

        Mockito.doNothing().when(carController).updateCar(updateCar.getId(), updateCar);
        mockMvc.perform(MockMvcRequestBuilders.put(endpoint)
                                              .contentType("application/json")
                                              .content(objectMapper.writeValueAsString(updateCar)))
                                            .andExpect(status().isOk());
    }

    @Test
    public void testDeleteCar() throws Exception {
        UUID uuID = carList.get(2).getId();
        String strID = uuID.toString();

        endpoint = endpoint + strID;
        Mockito.doNothing().when(carController).deleteCar(uuID);
        mockMvc.perform(MockMvcRequestBuilders.delete(endpoint)).andExpect(status().isOk());
        
        Mockito.verify(carController, times(1)).deleteCar(uuID);
    }
}
