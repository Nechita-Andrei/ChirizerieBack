package com.findork.chiriezerie.controller;

import com.findork.chiriezerie.exception.AppException;
import com.findork.chiriezerie.feature.account.User;
import com.findork.chiriezerie.model.Apartment;
import com.findork.chiriezerie.model.daos.ApartmentDao;
import com.findork.chiriezerie.service.ApartmentService;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/apartments")
@AllArgsConstructor
public class ApartmentController {

    private final ApartmentService apartmentService;

    @GetMapping("")
    public List<ApartmentDao> getAll() {
        log.info("Getting all apartments");
        return apartmentService.getAll().stream().map(ApartmentDao::new).collect(Collectors.toList());
    }
    
    @GetMapping("/filter")
    public List<ApartmentDao> getFilteredApartments(@RequestParam(required = false) String city,
                                                    @RequestParam(required = false) Integer rooms,
                                                    @RequestParam(required = false, defaultValue = "0") Integer minPrice,
                                                    @RequestParam(required = false) Integer maxPrice,
                                                    @RequestParam(required = false, defaultValue = "0") Integer minSquareFeet,
                                                    @RequestParam(required = false) Integer maxSquareFeet) {
        log.info("Getting filtered apartments");
        return apartmentService.getFilteredApartments(city, rooms, minPrice, maxPrice, minSquareFeet, maxSquareFeet);
    }

    @GetMapping("/{id}")
    public ApartmentDao getOne(@PathVariable Long id) {
        log.info("Getting apartment by apartmentId={}", id);
        Apartment apartment = apartmentService.getById(id);
        if (apartment == null) {
            throw new AppException("Apartment not found", HttpStatus.NOT_FOUND);
        }
        return new ApartmentDao(apartment);
    }

    @DeleteMapping("/{id}")
    public ApartmentDao delete(@PathVariable Long id) {
        log.info("Deleting apartment by apartmentId={}", id);
        Apartment apartment = apartmentService.deleteById(id);
        if (apartment == null) {
            throw new AppException("Apartment not found", HttpStatus.NOT_FOUND);
        }
        return new ApartmentDao(apartment);
    }

    @PostMapping("")
    public ApartmentDao save(@RequestBody ApartmentDao apartmentDao, User user) {
        log.info("Saving apartment");
        return new ApartmentDao(apartmentService.saveOrUpdate(apartmentDao, user));
    }
}
