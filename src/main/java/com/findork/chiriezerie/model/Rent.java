package com.findork.chiriezerie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "rent")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne()
    @JoinColumn(name = "apartment_id", referencedColumnName = "id")
    private Apartment apartment;

    @OneToOne()
    @JoinColumn(name = "tenant_id", referencedColumnName = "id")
    private Tenant tenant;

    @Column(name="number_of_rented_months")
    private Integer numberOfRentedMonths;
}
