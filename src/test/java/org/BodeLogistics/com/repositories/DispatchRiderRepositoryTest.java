package org.BodeLogistics.com.repositories;

import org.BodeLogistics.com.data.models.DispatchRider;
import org.BodeLogistics.com.data.repositories.DispatchRiderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class DispatchRiderRepositoryTest {
    @Autowired
    DispatchRiderRepository dispatchRiderRepository;
    private DispatchRider dispatchRider;

    @BeforeEach
    public void setUp() {
        dispatchRiderRepository.deleteAll();
        dispatchRider = new DispatchRider();
        dispatchRider.setPhoneNumber("11111111111");


    }
    @Test
    public void testDispatchRiderRepositoryIsEmpty() {
        assertTrue(dispatchRiderRepository.findAll().isEmpty());
    }
    @Test
    public void testDispatchRiderRepositoryIsNotEmpty() {
        dispatchRiderRepository.save(new DispatchRider());
        assertFalse(dispatchRiderRepository.findAll().isEmpty());
    }
    @Test
    public void testFindById() {
        dispatchRiderRepository.save(dispatchRider);
        assertTrue(dispatchRiderRepository.findById(dispatchRider.getId()).isPresent());
    }
    @Test
    public void testFindByPhoneNumber() {
        dispatchRiderRepository.save(dispatchRider);
        assertTrue(dispatchRiderRepository.findByPhoneNumber(dispatchRider.getPhoneNumber()).isPresent());
    }
}
