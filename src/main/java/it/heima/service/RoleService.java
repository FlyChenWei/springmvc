package it.heima.service;

import it.heima.domain.Role;

import java.util.List;


public interface RoleService {

    public List<Role> list();

    void save(Role role);
}
