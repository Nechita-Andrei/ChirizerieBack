package com.findork.chiriezerie.service.implementation;

import com.findork.chiriezerie.feature.account.User;
import com.findork.chiriezerie.model.Apartment;
import com.findork.chiriezerie.model.daos.ApartmentDao;
import com.findork.chiriezerie.repository.ApartmentRepository;
import com.findork.chiriezerie.repository.UserRepository;
import com.findork.chiriezerie.service.ApartmentService;
import com.google.gson.Gson;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final Gson gson;
    private final ApartmentRepository apartmentRepository;
    private final UserRepository userRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String FLUSK_URL_SET = "http://localhost:5000/set_apartments";
    private static final String FLUSK_URL_SUGGESTIONS = "http://localhost:5000/suggestions";
    private boolean initializedApartments = false;

    public ApartmentServiceImpl(Gson gson, ApartmentRepository apartmentRepository, UserRepository userRepository) {
        this.gson = gson;
        this.apartmentRepository = apartmentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Apartment> getAll() {
        if (!initializedApartments) {
            saveApartments();
            initializedApartments = true;
        }
        return apartmentRepository.findAll();
    }

    @Override
    public List<ApartmentDao> getSuggestionsByApartmentId(Long apartmentId) {
        ApartmentDao currentApartment = new ApartmentDao(apartmentRepository.findById(apartmentId).get());
        HttpEntity<ApartmentDao> request = new HttpEntity<>(currentApartment);
        ApartmentDao[] suggestions = restTemplate.postForObject(FLUSK_URL_SUGGESTIONS, request, ApartmentDao[].class);
        return Arrays.asList(suggestions);
    }

    private void saveApartments() {
        List<ApartmentDao> allApartments = apartmentRepository
                .findAll()
                .stream()
                .map(a -> {
                    ApartmentDao ad = new ApartmentDao(a);
                    ad.setPictureList(new ArrayList<>());
                    return ad;
                })
                .collect(Collectors.toList());
        HttpEntity<List<ApartmentDao>> request = new HttpEntity<>(allApartments);

        restTemplate.postForObject(FLUSK_URL_SET, request, Void.class);
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

    @Override
    public List<ApartmentDao> getFilteredApartments(String city, Integer rooms, Integer minPrice, Integer maxPrice, Integer minSquareFeet, Integer maxSquareFeet) {
        return apartmentRepository.findAll().stream()
                .filter(apartment -> {
                    if (city != null)
                        return apartment.getCity().toLowerCase().contains(city.toLowerCase());
                    return true;
                })
                .filter(apartment -> {
                    if (rooms != null) {
                        return apartment.getRooms().equals(rooms);
                    }
                    return true;
                })
                .filter(apartment -> {
                    if (minPrice == null) {
                        return true;
                    }
                    if (maxPrice == null) {
                        return apartment.getPrice() >= minPrice;
                    }
                    return minPrice <= apartment.getPrice() && apartment.getPrice() <= maxPrice;
                })
                .filter(apartment -> {
                    if (minSquareFeet == null) {
                        return true;
                    }
                    if (maxSquareFeet == null) {
                        return apartment.getSquareFeet() >= minSquareFeet;
                    }
                    return minSquareFeet <= apartment.getSquareFeet() && apartment.getSquareFeet() <= maxSquareFeet;
                })
                .map(ApartmentDao::new)
                .collect(Collectors.toList());
    }
}
