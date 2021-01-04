package com.findork.chiriezerie.feature.account.role;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getUserRole(RoleName roleName) {
        return roleRepository.findByName(roleName);
    }

    public Role getAdminRole() {
        return roleRepository.findByName(RoleName.ROLE_ADMIN);
    }
}
