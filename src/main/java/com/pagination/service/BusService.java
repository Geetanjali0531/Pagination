package com.pagination.service;

import com.pagination.entity.Bus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface BusService {
    Page<Bus> searchBuses(String fromLocation, String toLocation, LocalDate travelDate, Pageable pageable);

    Bus createBus(Bus bus);
    List<Bus> createBuses(List<Bus> buses);


}
