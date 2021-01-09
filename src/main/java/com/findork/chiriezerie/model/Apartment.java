package com.findork.chiriezerie.model;

import com.findork.chiriezerie.feature.account.User;
import com.findork.chiriezerie.model.daos.ApartmentDao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "apartment")
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "squarefeet")
    private Integer squareFeet;

    @Column(name = "address")
    private String address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "city")
    private String city;

    @Column(name = "details")
    private String details;

    //json list of base64 pictures
    @Column(name = "pictures")
    private String pictures;
    
    @Column(name = "price")
    private Double price;
    
    @Column(name = "rooms")
    private Integer rooms;

    public Apartment(ApartmentDao apartmentDao) {
        this.id = apartmentDao.getId();
        this.squareFeet = apartmentDao.getSquareFeet();
        this.address = apartmentDao.getAddress();
        this.city = apartmentDao.getCity();
        this.details = apartmentDao.getDetails();
        this.price = apartmentDao.getPrice();
        this.rooms = apartmentDao.getRooms();
    }
}
