package com.isoft.dao;

import com.isoft.dbutil.DBUtils;
import com.isoft.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public class UserDAO_1 {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public List<User> searchUserByRole(String role){
        String sql="select * from tb_login where role like ?";
        List<User> list=jdbcTemplate.query(sql,new BeanPropertyRowMapper<User>(User.class),new String[]{"%"+role+"%"});
        return list;
    }
    public int addUserInfo(String name,String runame,String role){
        String sql1="insert into tb_login(uname,role) values(?,?)";
        String sql2="insert into tb_user(uname,runame) values(?,?)";
        int rowNum1 = jdbcTemplate.update(sql1,name,role);
        int rowNum2 = jdbcTemplate.update(sql2,name,runame);
        if(rowNum1>0 && rowNum2>0)
            return 1;
        else
            return 0;
    }
    public int deleteUserById(String id){
        String sql="delete from tb_login where id=?";
        int update = jdbcTemplate.update(sql, new String[]{id});
        return update;
    }
    public List<Map<String, Object>> findAllUser(){
        String sql="select * from tb_user inner join tb_login on tb_user.uname=tb_login.uname";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }
    public int login(User user) {
        String sql="select * from tb_login where uname=? and upwd=md5(?)";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql,new String[]{user.getUname(),user.getUpwd()});
        return maps.size();
    }
}
