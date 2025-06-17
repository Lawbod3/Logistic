package org.BodeLogistics.com.data.repositories;

import org.BodeLogistics.com.data.models.RideActivity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RideActivityRepository extends MongoRepository<RideActivity, String> {

    Optional<List<RideActivity>> findAllByUserId(String userId);
    Optional<List<RideActivity>> findAllByDate(LocalDate date);
    Optional<List<RideActivity>> findByDriverId(String driverId);

}
