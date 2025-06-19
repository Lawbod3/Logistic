package org.BodeLogistics.com.repositories;

import org.BodeLogistics.com.data.models.Driver;
import org.BodeLogistics.com.data.repositories.DriverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class DriverRepositoryTest {
    @Autowired
    private DriverRepository driverRepository;
    private Driver driver;

    @BeforeEach
    public void setUp() {
        driverRepository.deleteAll();
        driver = new Driver();
        driver.setFirstName("John");
        driver.setLastName("Doe");
        driver.setEmail("john@doe.com");
        driver.setPassword("password");

    }

    @Test
    public void testDriverRepositoryIsEmpty() {
        assertTrue(driverRepository.findAll().isEmpty());
    }

    @Test
    public void testDriverRepositoryIsNotEmpty() {
        driverRepository.save(new Driver());
        assertFalse(driverRepository.findAll().isEmpty());
    }
    @Test
    public void testDriverRepositoryCanFindById() {
        driverRepository.save(driver);
        assertTrue(driverRepository.findById(driver.getId()).isPresent());
    }
    @Test
    public void testDriverRepositoryCanFindByEmail() {
        driverRepository.save(driver);
        assertTrue(driverRepository.findByEmail(driver.getEmail()).isPresent());
    }
    @Test
    public void testDriverRepositoryCanFindByPhoneNumber() {
        driverRepository.save(driver);
        assertTrue(driverRepository.findByPhoneNumber(driver.getPhoneNumber()).isPresent());
    }
}
