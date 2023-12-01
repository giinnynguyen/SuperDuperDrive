package com.udacity.vehicles.domain.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// JpaRepository: extends CRUDRepository and provides JPA related methods such as flushing, deleting in a batch, and pagination
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}
