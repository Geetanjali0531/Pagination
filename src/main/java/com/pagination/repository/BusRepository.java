package com.pagination.repository;

import com.pagination.entity.Bus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BusRepository extends JpaRepository<Bus,Long> {

    @Query("SELECT b FROM Bus b WHERE b.fromLocation = :fromLocation AND b.toLocation = :toLocation AND b.travelDate = :travelDate")
    Page<Bus> findByFromLocationAndToLocationAndTravelDate(
            @Param("fromLocation") String fromLocation,
            @Param("toLocation") String toLocation,
            @Param("travelDate") LocalDate travelDate,
            Pageable pageable);
}
