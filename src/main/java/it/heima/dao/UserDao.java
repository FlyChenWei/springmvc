package it.heima.dao;

import it.heima.domain.User;

import java.util.List;

/**
 * @author shk
 * @create 2021--07--02--17:00
 */
public interface UserDao {

    List<User> list();

    Long save(User user);

    void saveRelation(Long id, Long[] roleIds);

    void delUserRelation(Long userId);

    void delUser(Long userId);

    Boolean login(User user);
}
