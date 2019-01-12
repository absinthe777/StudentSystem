package com.isoft.service;

import com.isoft.dao.UserDAO_1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserDAO_1 userDAO_1;

    public List<Map<String, Object>> searchUserByRole(String role){
        return userDAO_1.searchUserByRole(role);
    }

    public boolean addUserInfo(String uname,String upwd,String runame,String role){
        int i = userDAO_1.addUserInfo(uname,upwd,runame,role);
        if (i>0)
            return true;
        else
            return false;
    }
    public boolean updateUserInfo(String uname,String runame,String role,String id){
       return userDAO_1.updateUserInfo(uname,runame,role,id);
    }
    public boolean deleteUserById(String id){
        int i = userDAO_1.deleteUserById(id);
        if (i>0)
            return true;
        else
            return false;
    }
    public List<Map<String, Object>> findAllUser(){
        return userDAO_1.findAllUser();
    }
    public int login(String uname,String upwd){
        return userDAO_1.login(uname,upwd);
    }
}
