package com.lim.req;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@WebServlet("/index")
public class HttpServletTest extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post...");
        //req
        //1.请求头
        //http动词
        System.out.println("http动词:"+req.getMethod());
        //虚拟路径
        System.out.println("虚拟路径:"+req.getContextPath());
        //URL
        System.out.println("URL:"+req.getRequestURL());
        //URI
        System.out.println("URI:"+req.getRequestURI());
        //查询参数

        //2.请求行
        System.out.println(req.getHeader("User-Agent"));

        //3.请求体 -- 流对象读取

        //获取请求get,post参数通用方式
        /*
        req.getParameterMap();
        req.getParameterValues("key");
        req.getParameter("key");
        */
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get...");
        //req
        //1.请求头
        //http动词
        System.out.println("http动词:"+req.getMethod());
        //虚拟路径
        System.out.println("虚拟路径:"+req.getContextPath());
        //URL
        System.out.println("URL:"+req.getRequestURL());
        //URI
        System.out.println("URI:"+req.getRequestURI());
        //查询参数
        String s = URLDecoder.decode(req.getQueryString(),StandardCharsets.UTF_8);
        System.out.println("查询参数:"+s);

        //2.请求行
        System.out.println(req.getHeader("User-Agent"));
    }
}
