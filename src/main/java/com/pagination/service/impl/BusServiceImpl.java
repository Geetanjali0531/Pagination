package com.pagination.service.impl;

import com.pagination.entity.Bus;
import com.pagination.repository.BusRepository;
import com.pagination.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class BusServiceImpl implements BusService {


    @Autowired
    private BusRepository busRepository;


    @Override
    public Page<Bus> searchBuses(String fromLocation, String toLocation, LocalDate travelDate, Pageable pageable) {
        return busRepository.findByFromLocationAndToLocationAndTravelDate(fromLocation, toLocation, travelDate, pageable);

    }


    @Override
    public Bus createBus(Bus bus) {
        return busRepository.save(bus);
    }

    @Override
    public List<Bus> createBuses(List<Bus> buses) {
        return busRepository.saveAll(buses);
    }


}
