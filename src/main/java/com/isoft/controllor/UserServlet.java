package com.isoft.controllor;

import com.isoft.pojo.User;
import com.isoft.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserServlet",urlPatterns = "/userServlet")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String uname=request.getParameter("uname");
        String upwd=request.getParameter("upwd");
        String role=request.getParameter("role");
//        从服务获取值
        UserService userService=new UserService();
//        硬编码
        User user=new User();
        user.setUname(uname);
        user.setUpwd(upwd);
        user.setRole(role);


        if (userService.login(user)){
            List<Map<String, Object>> allUser=userService.findAllUser();
            //session
            HttpSession session=request.getSession();
            session.setAttribute("userList",allUser);
            response.sendRedirect("showUserInfo.jsp");
        }else {
            response.sendRedirect("login.html");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println("my servlet");
        String uname=request.getParameter("uname");
        String upwd=request.getParameter("upwd");
        String role=request.getParameter("role");
        System.out.println(uname+"-"+upwd+"-"+role);
        UserService userService=new UserService();
        User user=new User();
        user.setUname(uname);
        user.setUpwd(upwd);
        user.setRole(role);
        boolean login = userService.login(user);
        PrintWriter out = response.getWriter();
        if (login)
            out.print("777");
        else
            out.print("444");
        out.close();
    }
}
