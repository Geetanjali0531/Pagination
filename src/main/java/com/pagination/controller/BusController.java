package com.pagination.controller;


import com.pagination.entity.Bus;
import com.pagination.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping("/search")
    public ResponseEntity<Page<Bus>> searchBuses(
            @RequestParam String fromLocation,
            @RequestParam String toLocation,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate travelDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Bus> busPage = busService.searchBuses(fromLocation, toLocation, travelDate, pageable);
        return ResponseEntity.ok(busPage);
    }


    @PostMapping("/buses")
    public ResponseEntity<Bus> createBus(@RequestBody Bus bus) {
        Bus savedBus = busService.createBus(bus);
        return ResponseEntity.ok(savedBus);
    }

    @PostMapping("/buses/batch")
    public ResponseEntity<List<Bus>> createBuses(@RequestBody List<Bus> buses) {
        List<Bus> savedBuses = busService.createBuses(buses);
        return ResponseEntity.ok(savedBuses);
    }


}
