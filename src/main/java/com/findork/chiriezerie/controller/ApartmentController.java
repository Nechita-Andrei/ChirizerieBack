package com.findork.chiriezerie.controller;

import com.findork.chiriezerie.exception.AppException;
import com.findork.chiriezerie.model.Apartment;
import com.findork.chiriezerie.service.IApartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/apartments")
@AllArgsConstructor
public class ApartmentController {

    private final IApartmentService apartmentService;

    @GetMapping("/")
    public List<Apartment> getAll(){
        return apartmentService.getAll();
    }

    @GetMapping("/{id}")
    public Apartment getOne(@PathVariable Integer id){
        Apartment apartment =  apartmentService.getById(id);
        if(apartment == null){
            return new Apartment();
//            throw new AppException("Apartment not found", HttpStatus.NOT_FOUND);
        }
        return apartment;
    }

    @DeleteMapping("/{id}")
    public Apartment delete(@PathVariable Integer id){
        Apartment apartment =  apartmentService.deleteById(id);
        if(apartment == null){
            throw new AppException("Apartment not found", HttpStatus.NOT_FOUND);
        }
        return apartment;
    }

    @PostMapping("/")
    public Apartment salve(@RequestBody Apartment apartment){
        if(apartment.getId() == null){
            apartment.setId(0);
        }
        return apartmentService.saveOrUpdate(apartment);
    }

}
