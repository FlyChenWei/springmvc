package it.heima.dao.impl;

import it.heima.dao.RoleDao;
import it.heima.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoimpl implements RoleDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Role> findAll() {
        System.out.println("成功come in findAll...");
        List<Role> roleList = jdbcTemplate.query("select * from sys_role",
                new BeanPropertyRowMapper<Role>(Role.class));
        return roleList;
    }

    public void save(Role role) {
        System.out.println(role);
        jdbcTemplate.update("insert into sys_role values(?,?,?)", null, role.getRoleName(), role.getRoleDesc());
    }

    //这是用户的id
    public List<Role> queryRoleByUserId(Long id) {
        List<Role> roleList = jdbcTemplate.query("SELECT*FROM sys_role INNER JOIN sys_user_role ON sys_role.id=sys_user_role.roleId WHERE sys_user_role.userId=?"
                , new BeanPropertyRowMapper<Role>(Role.class), id);
        return roleList;
    }

}
