package com.isoft.controllor;

import com.isoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class UserControllor {
    @Autowired
    UserService userService;
    @RequestMapping("/login.do")
    @ResponseBody
    public boolean login(String uname, String upwd){
        int a = userService.login(uname,upwd);
        if (a > 0) {
            return true;
        } else {
            return false;
        }
    }
    @RequestMapping("/findAllUser.do")
    @ResponseBody
    public List findAllUser(){
        List<Map<String, Object>> allUser = userService.findAllUser();
        return allUser;
    }
    @RequestMapping("/searchUserByRole.do")
    @ResponseBody
    public List<Map<String, Object>> searchUserByRole(String role){
        List<Map<String, Object>> search = userService.searchUserByRole(role);
        return search;
    }
    @RequestMapping("/addUserInfo.do")
    @ResponseBody
    public boolean addUserInfo(String uname,String upwd,String runame,String role){
        boolean b = userService.addUserInfo(uname,upwd,runame, role);
        return b;
    }
    @RequestMapping("/deleteUserById.do")
    @ResponseBody
    public boolean deleteUserById(@RequestParam("id") String id){
        boolean temp = userService.deleteUserById(id);
        return temp;
    }
    @RequestMapping("/updateUserInfo.do")
    @ResponseBody
    public boolean updateUserInfo(String uname,String runame,String role,String id){
        boolean u=userService.updateUserInfo(uname,runame,role,id);
        return u;
    }
}
