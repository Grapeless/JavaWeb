package com.lim;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lim.mapper.EmpMapper;
import com.lim.pojo.Emp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootWebManagementApplicationTests {


    /*@Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("name","Tom");

        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256,"qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjkl")
                .expiration(new Date(System.currentTimeMillis() + 3600 * 1000))
                .compact();

        System.out.println(jwt);
    }
    @Test
    public void testParerJwt() {
        Claims claims = Jwts.parser()
                .setSigningKey("qwertyuiopasdfghjklzxcvbnmqwertyuiopasdfghjkl")
                .build()
                .parseSignedClaims("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiVG9tIiwiaWQiOjEsImV4cCI6MTc0MDMxNjgzMn0.m8fiOFGenD74dd4xtWuJyviij9SZxEqWHX_AafTD6kk")
                .getBody();

        System.out.println(claims);
    }*/

    @Autowired
    private EmpMapper empMapper;
    @Test
    void testPagingQuery(){
        Page<Emp> page = new Page<>(1, 5);
        empMapper.selectPage(page,null);
        page.getPages();
        page.getTotal();
        page.getCurrent();
        page.getRecords();
    }
}
