package com.lim.res;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/res01")
public class ResponseTest01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //重定向

        /*//1.设置响应行的状态码
        resp.setStatus(302);
        //2.设置响应头之--Location的值:目标资源的位置
        resp.setHeader("Location","/res02");*/

        //3 == 1 + 2
        resp.sendRedirect("res02");
    }
}
