package com.example.facebook.service.impl;

import com.example.facebook.entity.Role;
import com.example.facebook.repository.RoleRepository;
import com.example.facebook.service.contract.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getUserRole() {
        Role userRole = roleRepository.findAllByRoleName("ROLE_USER")
                .orElseThrow(() -> new IllegalStateException("User role not found"));
        return userRole;
    }

}
