package com.jogger.rs.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementacija servisa za rad sa ulogama sistema.
 *
 * @author Jovan Virijevic
 */
@Service
public class RoleService implements RoleServiceInterface{

    /**
     * Repozitorijum za rad sa ulogama.
     */
    private RoleRepository roleRepository;

    /**
     * Javni konstruktor.
     *
     * @param roleRepository repozitorijum za rad sa ulogama
     */
    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findRolesByNames(List<String> roleNames) {
        return roleRepository.findRolesByRoleNames(roleNames);
    }
}
