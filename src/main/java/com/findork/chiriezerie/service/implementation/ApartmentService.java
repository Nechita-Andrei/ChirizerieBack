package com.findork.chiriezerie.service.implementation;

import com.findork.chiriezerie.model.Apartment;
import com.findork.chiriezerie.repository.ApartmentRepository;
import com.findork.chiriezerie.service.IApartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApartmentService implements IApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Override
    public List<Apartment> getAll() {
        return apartmentRepository.findAll();
    }

    @Override
    public Apartment getById(Integer id) {
        return apartmentRepository.findById(id).orElse(null);
    }

    @Override
    public Apartment deleteById(Integer id) {
        Apartment apartment = apartmentRepository.findById(id).orElse(null);
        apartmentRepository.deleteById(id);
        return apartment;
    }

    @Override
    public Apartment saveOrUpdate(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }
}
