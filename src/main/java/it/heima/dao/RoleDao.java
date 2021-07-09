package it.heima.dao;

import it.heima.domain.Role;

import java.util.List;

/**
 * @author shk
 * @create 2021--07--02--14:24
 */
public interface RoleDao {
    List<Role> findAll();

    void save(Role role);

    List<Role> queryRoleByUserId(Long id);
}
