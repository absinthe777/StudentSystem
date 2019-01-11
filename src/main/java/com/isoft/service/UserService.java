package com.isoft.service;

import com.isoft.dao.UserDAO;
import com.isoft.dao.UserDAO_1;
import com.isoft.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserDAO_1 userDAO_1;

    public List<User> searchUserByRole(String role){
        return userDAO_1.searchUserByRole(role);
    }

    public boolean addUserInfo(String uname,String runame,String role){
        int i = userDAO_1.addUserInfo(uname,runame,role);
        if (i>0)
            return true;
        else
            return false;
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
    public boolean login(User user){
        int temp=userDAO.login(user);
        if (temp==0)
            return false;
        else
            return true;
    }
    public boolean login1(User user){
        int temp=userDAO_1.login(user);
        if (temp==0)
            return false;
        else
            return true;
    }
}
