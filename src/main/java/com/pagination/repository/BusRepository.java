package com.pagination.repository;

import com.pagination.entity.Bus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BusRepository extends JpaRepository<Bus,Long> {

    Page<Bus> findByFromLocationAndToLocationAndTravelDate(String fromLocation, String toLocation, LocalDate travelDate, Pageable pageable);

}
