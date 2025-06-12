package org.BodeLogistics.com.data.repositories;

import org.BodeLogistics.com.data.models.DispatchRider;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DispatchRiderRepository extends MongoRepository<DispatchRider,String> {

    Optional<DispatchRider> findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByMotorcycleId(String motorcycleId);
}
