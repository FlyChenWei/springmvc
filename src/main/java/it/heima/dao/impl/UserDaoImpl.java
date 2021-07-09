package it.heima.dao.impl;

import it.heima.dao.UserDao;
import it.heima.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> list() {
        List<User> userList = jdbcTemplate.query("select *from sys_user", new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    public Long save(final User user) {
        //创建preparedStatementCreator对象
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                //使用原始的jdbc创建preparedStatement对象
                PreparedStatement preparedStatement = connection.prepareStatement("insert into sys_user values(?,?,?,?,?)", 1);
                preparedStatement.setObject(1, null);
                preparedStatement.setObject(2, user.getUsername());
                preparedStatement.setObject(3, user.getEmail());
                preparedStatement.setObject(4, user.getPassword());
                preparedStatement.setObject(5, user.getPhoneNum());
                return preparedStatement;
            }
        };
        //创建1keyholder
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(creator, holder);
        //获得生成的主键
        long userId = holder.getKey().longValue();
        return userId;
    }

    public void saveRelation(Long userId, Long[] roleIds) {
        for (Long m : roleIds) {
            jdbcTemplate.update("insert into sys_user_role values(?,?)", userId, m);
        }
    }

    public void delUserRelation(Long userId) {
        jdbcTemplate.update("delete from sys_user_role where  userId=?", userId);
    }

    public void delUser(Long userId) {
        jdbcTemplate.update("delete from sys_user where id=?", userId);
    }

    public Boolean login(User user) throws EmptyResultDataAccessException {
        System.out.println(user);
        User query = jdbcTemplate.queryForObject("select * from sys_user where username=? and password= ?", new BeanPropertyRowMapper<User>(User.class), user.getUsername(), user.getPassword());
        System.out.println(query);
        return !(query==null);
    }

}
