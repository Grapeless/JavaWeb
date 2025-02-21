package com.lim;

import com.lim.mapper.EmpMapper;
import com.lim.pojo.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringbootMybatisCrudApplicationTests {

    @Autowired
    private EmpMapper empMapper;

    @Test
    public void testDeleteByID(){
        empMapper.deleteByID(17);
    }

    @Test
    public void testInsert(){
        Emp emp = new Emp();

        emp.setUsername("Tom2");
        emp.setName("汤姆2");
        emp.setImage("1.jpg");
        emp.setGender((short)1);
        emp.setJob((short)1);
        emp.setEntrydate(LocalDate.of(2000,1,1));
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(1);

        empMapper.insert(emp);
        System.out.println(emp.getId());
    }

    @Test
    public void testUpdate(){
        Emp emp = new Emp();

        emp.setId(18);
        emp.setUsername("Tom3333");
        emp.setName("汤姆3333");
        emp.setGender((short)2);
        emp.setEntrydate(LocalDate.of(2000,1,1));
        emp.setUpdateTime(LocalDateTime.now());

        empMapper.update(emp);

    }

    @Test
    public void testSelectByID(){
        Emp emp = empMapper.selectByID(12);
    }

    @Test
    public void testConditionalSelect(){
        /*List<Emp> empList = empMapper.complexSelect("张",(short) 1,
                LocalDate.of(2010,1,1),LocalDate.of(2020,1,1));*/

        List<Emp> empList = empMapper.complexSelect("张",(short) 1,
                null,null);
        System.out.println(empList);
    }

    @Test
    public void testDeleteByIDs(){
        List<Integer> idList = new ArrayList<>();
        idList.add(18);
        idList.add(19);
        empMapper.deleteByIDs(idList);
    }
}
