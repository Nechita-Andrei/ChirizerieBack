package com.findork.chiriezerie.model;

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
    @Column(name = "id")
    private Integer id;

    @Column(name = "squarefeet")
    private Integer squareFeet;

    @Column(name = "address")
    private String address;

    @OneToOne()
    @JoinColumn(name = "landLord_id", referencedColumnName = "id")
    private Landlord owner;

    @Column(name = "city")
    private String city;

    @Column(name = "details")
    private String details;

    //json list of base64 pictures
    @Column(name = "pictures")
    private String pictures;


    public Apartment(ApartmentDao apartmentDao) {
        this.id = apartmentDao.getId();
        this.squareFeet = apartmentDao.getSquareFeet();
        this.address = apartmentDao.getAddress();
        this.city = apartmentDao.getCity();
        this.details = apartmentDao.getDetails();
    }

}
