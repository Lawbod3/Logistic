package org.BodeLogistics.com.data.repositories;

import org.BodeLogistics.com.data.models.Activity;
import org.BodeLogistics.com.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {

    Optional<List<Activity>> findAllByUserId(String userId);
    Optional<List<Activity>> findAllByDate(LocalDate date);
    Optional<List<Activity>> findByDriverId(String driverId);

}
