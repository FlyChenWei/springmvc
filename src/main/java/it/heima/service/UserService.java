package it.heima.service;


import it.heima.domain.User;

import java.util.List;

public interface UserService {
    List<User> list();



    Long saveUser(User user);

    void saveRelation(Long id, Long[] roleIds);

    void delUserRelation(Long userId);

    void delUser(Long userId);

    Boolean login(User user);
}
