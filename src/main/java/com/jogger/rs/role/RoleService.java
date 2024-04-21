package com.jogger.rs.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementacija servisa za rad sa ulogama sistema.
 *
 * @author Jovan Virijevic
 */
@Service
@RequiredArgsConstructor
public class RoleService implements RoleServiceInterface{

    /**
     * Repozitorijum za rad sa ulogama.
     */
    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findRolesByNames(List<String> roleNames) {
        return roleRepository.findRolesByRoleNames(roleNames);
    }
}
