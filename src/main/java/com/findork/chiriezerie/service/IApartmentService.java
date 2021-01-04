package com.findork.chiriezerie.service;

import com.findork.chiriezerie.feature.account.User;
import com.findork.chiriezerie.model.Apartment;
import com.findork.chiriezerie.model.daos.ApartmentDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IApartmentService {

    List<Apartment> getAll();

    Apartment getById(Integer id);

    Apartment deleteById(Integer id);

    Apartment saveOrUpdate(ApartmentDao apartmentDao, User user);

}
