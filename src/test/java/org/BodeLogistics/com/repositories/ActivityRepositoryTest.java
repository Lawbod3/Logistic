package org.BodeLogistics.com.repositories;

import org.BodeLogistics.com.data.models.Activity;
import org.BodeLogistics.com.data.models.User;
import org.BodeLogistics.com.data.repositories.ActivityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
public class ActivityRepositoryTest {
    @Autowired
    ActivityRepository activityRepository;
    private User user;
    private Activity activity;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setADriver(true);

        activityRepository.deleteAll();
        activity = new Activity();
        activity.setDriver(user);
    }
    @Test
    public void testActivityRepositoryIsEmpty() {
        assertTrue(activityRepository.findAll().isEmpty());
    }
    @Test
    public void testActivityRepositoryIsNotEmpty() {
        activityRepository.save(new Activity());
        assertFalse(activityRepository.findAll().isEmpty());
    }
    @Test
    public void testActivityRepositoryCanFindById() {
        activityRepository.save(activity);
        assertTrue(activityRepository.findById(activity.getId()).isPresent());
    }
    @Test
    public void testActivityRepositoryCanFindByDriver() {
        activityRepository.save(activity);
        assertTrue(activityRepository.findByDriverId(activity.getDriver().getId()).isPresent());
    }


}
