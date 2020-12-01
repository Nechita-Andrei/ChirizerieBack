package com.findork.chiriezerie.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="squarefeet")
    private Integer squareFeet;

    @Column(name="address")
    private String address;

    @OneToOne()
    @JoinColumn(name = "landLord_id", referencedColumnName = "id")
    private Landlord owner;

    @Column(name="city")
    private String city;

    @Column(name="details")
    private String details;

    //json list of base64 pictures
    @Column(name="pictures")
    private String pictures;

    @Transient
    @JsonIgnore
    private List<Picture> pictureList;

    public void setPictures(String pictures) {
        this.pictures = pictures;
        Gson gson = new Gson();
        System.out.println(pictures);
        System.out.println(pictures);
        pictureList = gson.fromJson(pictures,  new TypeToken<List<String>>(){}.getType());
    }

    public List<Picture> getPictureList() {
        if(pictureList ==null){
            Gson gson = new Gson();
            pictureList = gson.fromJson(pictures,  new TypeToken<List<String>>(){}.getType());
        }
        return pictureList;
    }

}
