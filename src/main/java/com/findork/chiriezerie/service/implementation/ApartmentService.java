package com.findork.chiriezerie.service.implementation;

import com.findork.chiriezerie.feature.account.User;
import com.findork.chiriezerie.feature.account.UserRepository;
import com.findork.chiriezerie.model.Apartment;
import com.findork.chiriezerie.model.daos.ApartmentDao;
import com.findork.chiriezerie.repository.ApartmentRepository;
import com.findork.chiriezerie.service.IApartmentService;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ApartmentService implements IApartmentService {

    private final Gson gson;
    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;

    @Override
    public List<Apartment> getAll() {
        return apartmentRepository.findAll();
    }

    @Override
    public Apartment getById(Long id) {
        return apartmentRepository.findById(id).orElse(null);
    }

    @Override
    public Apartment deleteById(Long id) {
        Apartment apartment = apartmentRepository.findById(id).orElse(null);
        apartmentRepository.deleteById(id);
        return apartment;
    }

    @Override
    public Apartment saveOrUpdate(ApartmentDao apartmentDao, User user) {
        Apartment apartment = new Apartment(apartmentDao);
        apartment.setUser(userRepository.getOne(user.getId()));
        apartment.setPictures(gson.toJson(apartmentDao.getPictureList()));
        return apartmentRepository.save(apartment);
    }
}
