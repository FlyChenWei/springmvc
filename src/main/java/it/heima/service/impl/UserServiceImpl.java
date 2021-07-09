package it.heima.service.impl;

import it.heima.dao.RoleDao;
import it.heima.dao.UserDao;
import it.heima.domain.Role;
import it.heima.domain.User;
import it.heima.service.UserService;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    public List<User> list() {

        List<User> userList = userDao.list();
        for (User m : userList) {
            List<Role> roleList = roleDao.queryRoleByUserId(m.getId());
            m.setRoleList(roleList);
        }
        return userList;
    }


    public Long saveUser(User user) {
        Long save = userDao.save(user);
        return save;
    }

    public void saveRelation(Long id, Long[] roleIds) {
        userDao.saveRelation(id, roleIds);
    }

    public void delUserRelation(Long userId) {
        userDao.delUserRelation(userId);
    }

    public void delUser(Long userId) {
        userDao.delUser(userId);
    }

    public Boolean login(User user) {
        try{
            Boolean flag=userDao.login(user);
            return flag;
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }

    }


}
