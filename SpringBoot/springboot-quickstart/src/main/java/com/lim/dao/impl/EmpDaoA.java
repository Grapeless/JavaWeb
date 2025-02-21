package com.lim.dao.impl;

import com.lim.dao.EmpDao;
import com.lim.pojo.Emp;
import com.lim.utils.XmlParserUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class EmpDaoA implements EmpDao {
    @Override
    public List<Emp> getEmpList() {
        String file = Objects.requireNonNull(this.getClass().getClassLoader().getResource("emp.xml")).getFile();
        return XmlParserUtils.parse(file, Emp.class);
    }
}
