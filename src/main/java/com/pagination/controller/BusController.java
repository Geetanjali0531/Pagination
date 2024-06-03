package com.pagination.controller;


import com.pagination.entity.Bus;
import com.pagination.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping("/search")
    public ResponseEntity<?> searchBuses(
            @RequestParam String fromLocation,
            @RequestParam String toLocation,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate travelDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Page<Bus> buses = busService.searchAllBuses(fromLocation, toLocation, travelDate, page, size, sortBy, sortDir);

        HashMap<String, Object> hm = new HashMap<>();
        hm.put("content", buses.getContent());
        hm.put("pageNo", buses.getNumber());
        hm.put("pageSize", buses.getSize());
        hm.put("total-Elements", buses.getTotalElements());
        hm.entrySet().stream().forEach(x -> System.out.println(x.getKey() + x.getValue()));
        return ResponseEntity.ok().body(hm);


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
