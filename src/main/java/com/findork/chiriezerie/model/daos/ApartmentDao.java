package com.findork.chiriezerie.model.daos;

import com.findork.chiriezerie.model.Apartment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ApartmentDao {

    private Long id;
    private Integer squareFeet;
    private String address;
    private Long userId;
    private String city;
    private String details;
    private Double price;
    private Integer rooms;
    private List<String> pictureList;


    public ApartmentDao(Apartment apartment) {
        this.id = apartment.getId();
        this.squareFeet = apartment.getSquareFeet();
        this.address = apartment.getAddress();
        this.userId = apartment.getUser().getId();
        this.city = apartment.getCity();
        this.details = apartment.getDetails();
        this.rooms = apartment.getRooms();
        this.price = apartment.getPrice();
        Gson gson = new Gson();
        pictureList = gson.fromJson(apartment.getPictures(), new TypeToken<List<String>>() {}.getType());
    }
}
