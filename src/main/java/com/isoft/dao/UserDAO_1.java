package com.isoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserDAO_1 {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> searchUserByRole(String role) {
        String sql = "select * from tb_login where role like ?";
        List<Map<String, Object>> search = jdbcTemplate.queryForList(sql, new String[]{"%" + role + "%"});
        return search;
    }

    public int addUserInfo(String name, String upwd, String runame, String role) {
        String sql1 = "insert into tb_login(uname,upwd,lastLoginTime,role) values(?,md5(?),now(),?)";
        String sql2 = "insert into tb_user(uname,runame) values(?,?)";
        int rowNum1 = jdbcTemplate.update(sql1, name, "777777", role);
        int rowNum2 = jdbcTemplate.update(sql2, name, runame);
        if (rowNum1 > 0 && rowNum2 > 0)
            return 1;
        else
            return 0;
    }

    public boolean updateUserInfo(String uname, String runame, String role, String id) {
        String sql1 = "UPDATE tb_login AS a INNER JOIN (SELECT uname,runame FROM tb_user) AS b ON a.uname = b.uname SET a.uname =? where a.id=?";
        String sql2 = "UPDATE tb_login set role=? where id=?";
        String sql3 = "UPDATE tb_user AS a SET a.runame =? where a.uname=(select uname from tb_login where id=?)";
        try {
            int rowNum1 = jdbcTemplate.update(sql1, uname,String.valueOf(id));
            int rowNum2 = jdbcTemplate.update(sql2, role, String.valueOf(id));
            int rowNum3 = jdbcTemplate.update(sql3, runame,String.valueOf(id));
            if (rowNum1 > 0 && rowNum2>0 && rowNum3>0)
                return true;
            else
                return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

    }

    public int deleteUserById(String id) {
        String sql = "delete from tb_login where id=?";
        int update = jdbcTemplate.update(sql, new String[]{id});
        return update;
    }

    public List<Map<String, Object>> findAllUser() {
        String sql = "select * from tb_user inner join tb_login on tb_user.uname=tb_login.uname";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    public int login(String uname, String upwd) {
        String sql = "select * from tb_login where uname=? and upwd=md5(?)";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql, new String[]{uname, upwd});
        return maps.size();
    }
}
