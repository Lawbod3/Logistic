package org.BodeLogistics.com.data.repositories;

import org.BodeLogistics.com.data.models.DispatchActivity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface DispatchActivityRepository extends MongoRepository<DispatchActivity, String> {
    Optional<List<DispatchActivity>> findAllByUserId(String userId);
    Optional<List<DispatchActivity>> findAllByDate(LocalDate date);
    Optional<List<DispatchActivity>> findByDispatcherId(String driverId);
}
