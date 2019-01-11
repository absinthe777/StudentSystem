package com.isoft.controllor;

import com.alibaba.fastjson.JSON;
import com.isoft.pojo.User;
import com.isoft.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserServletForAjax",urlPatterns = "/userServletForAjax")
public class UserServletForAjax extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("uname");
        String role = request.getParameter("role");
        UserService userService=new UserService();
        User user=new User();
        user.setUname(uname);
        user.setRole(role);
//        boolean b=userService.addUserInfo(useru);
        PrintWriter out = response.getWriter();
//        out.print(b);
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService=new UserService();
        List<Map<String, Object>> allUser = userService.findAllUser();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        String jsonStr= JSON.toJSONString(allUser);//将对象转化为JSON字符串
        out.print(jsonStr);
        out.close();
    }
}
