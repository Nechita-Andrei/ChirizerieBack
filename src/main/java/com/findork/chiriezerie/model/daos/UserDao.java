package com.findork.chiriezerie.model.daos;

import com.findork.chiriezerie.feature.account.User;
import com.findork.chiriezerie.feature.account.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDao {

    private Long id;
    private String name;
    private String username;
    private String email;
    private String phoneNumber;
    private String profilePicture;
    private Set<Role> roles;
    private List<ApartmentDao> apartments = new ArrayList<>();

    public UserDao(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.phoneNumber = user.getPhoneNumber();
        this.roles = user.getRoles();
        this.profilePicture = user.getProfilePicture();
        if (user.getApartment() != null)
            user.getApartment().forEach(a -> apartments.add(new ApartmentDao(a)));
    }
}
