package com.findork.chiriezerie.controller;

import com.findork.chiriezerie.exception.AppException;
import com.findork.chiriezerie.model.Apartment;
import com.findork.chiriezerie.model.daos.ApartmentDao;
import com.findork.chiriezerie.service.IApartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/apartments")
@AllArgsConstructor
public class ApartmentController {

    private final IApartmentService apartmentService;

    @GetMapping("")
    public List<ApartmentDao> getAll() {
        return apartmentService.getAll().stream().map(ApartmentDao::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ApartmentDao getOne(@PathVariable Integer id) {
        Apartment apartment = apartmentService.getById(id);
        if (apartment == null) {
            throw new AppException("Apartment not found", HttpStatus.NOT_FOUND);
        }
        return new ApartmentDao(apartment);
    }

    @DeleteMapping("/{id}")
    public ApartmentDao delete(@PathVariable Integer id) {
        Apartment apartment = apartmentService.deleteById(id);
        if (apartment == null) {
            throw new AppException("Apartment not found", HttpStatus.NOT_FOUND);
        }
        return new ApartmentDao(apartment);
    }

    @PostMapping("")
    public ApartmentDao save(@RequestBody ApartmentDao apartmentDao) {
        if (apartmentDao.getId() == null) {
            apartmentDao.setId(0);
            apartmentDao.setOwnerId(1);
        }
        return new ApartmentDao(apartmentService.saveOrUpdate(apartmentDao));
    }

}
