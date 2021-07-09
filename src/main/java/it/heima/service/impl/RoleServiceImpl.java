package it.heima.service.impl;

import it.heima.dao.RoleDao;
import it.heima.domain.Role;
import it.heima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
@Autowired
    private RoleDao roleDao;


    public List <Role> list() {
        List<Role> roleList=roleDao.findAll();
        return roleList;
    }

    public void save(Role role) {
roleDao.save(role);
    }

}
