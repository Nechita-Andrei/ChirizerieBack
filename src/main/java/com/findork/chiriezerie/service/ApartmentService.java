package com.findork.chiriezerie.service;

import com.findork.chiriezerie.feature.account.User;
import com.findork.chiriezerie.model.Apartment;
import com.findork.chiriezerie.model.daos.ApartmentDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApartmentService {

    List<Apartment> getAll();

    List<ApartmentDao> getSuggestionsByApartmentId(Long apartmentId);

    Apartment getById(Long id);

    Apartment deleteById(Long id);

    Apartment saveOrUpdate(ApartmentDao apartmentDao, User user);

    List<ApartmentDao> getFilteredApartments(String city, Integer rooms, Integer minPrice, Integer maxPrice, Integer minSquareFeet, Integer maxSquareFeet);
}
