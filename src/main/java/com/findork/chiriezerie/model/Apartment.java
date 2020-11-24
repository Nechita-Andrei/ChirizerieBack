package com.findork.chiriezerie.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
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
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id")
    private Landlord owner;

    @Column(name="city")
    private String city;

    @Column(name="details")
    private String details;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "apartment_id")
    private List<Picture> pictureList;


}
