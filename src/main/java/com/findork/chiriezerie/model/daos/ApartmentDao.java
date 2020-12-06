package com.findork.chiriezerie.model.daos;

import com.findork.chiriezerie.model.Apartment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ApartmentDao {

    private Integer id;
    private Integer squareFeet;
    private String address;
    private Integer ownerId;
    private String city;
    private String details;
    private List<String> pictureList;


    public ApartmentDao(Apartment apartment) {
        this.id = apartment.getId();
        this.squareFeet = apartment.getSquareFeet();
        this.address = apartment.getAddress();
        this.ownerId = apartment.getOwner().getId();
        this.city = apartment.getCity();
        this.details = apartment.getDetails();
        Gson gson = new Gson();
        pictureList = gson.fromJson(apartment.getPictures(), new TypeToken<List<String>>() {}.getType());
    }
}
