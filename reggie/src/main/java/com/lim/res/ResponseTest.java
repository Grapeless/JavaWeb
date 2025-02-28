package com.lim.res;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/res")
public class ResponseTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.输出字符输出流
        //setContentType,getWriter
        resp.setContentType("text/html;charset=utf-8");

        resp.getWriter().write("Hello");
        resp.getWriter().write("中文");
        resp.getWriter().write("<h1>Title</h1>");

        //2.输出字节输出流
    }
}
