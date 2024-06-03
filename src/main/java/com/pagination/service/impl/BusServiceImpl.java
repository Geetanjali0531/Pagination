package com.pagination.service.impl;

import com.pagination.entity.Bus;
import com.pagination.repository.BusRepository;
import com.pagination.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class BusServiceImpl implements BusService {


    @Autowired
    private BusRepository busRepository;



    @Override
    public Page<Bus> searchAllBuses(String fromLocation, String toLocation, LocalDate travelDate,int page, int size, String sortBy, String sortDir) {
        Sort sort=Sort.by(Sort.Direction.fromString(sortDir),sortBy);
        Pageable pageable=PageRequest.of(page,size,sort);
        Page<Bus> byFromLocationAndToLocationAndTravelDate = busRepository.findByFromLocationAndToLocationAndTravelDate(fromLocation, toLocation, travelDate,pageable);
        return byFromLocationAndToLocationAndTravelDate;
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
