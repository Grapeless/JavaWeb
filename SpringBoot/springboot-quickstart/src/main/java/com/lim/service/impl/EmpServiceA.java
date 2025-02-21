package com.lim.service.impl;

import com.lim.dao.EmpDao;
import com.lim.dao.impl.EmpDaoA;
import com.lim.pojo.Emp;
import com.lim.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service(value = "ESA")
//@Primary
@Service
public class EmpServiceA implements EmpService {
    @Autowired
    private EmpDao empDao;
    @Override
    public List<Emp> handleEmpList() {
        List<Emp> empList =  empDao.getEmpList();
        empList.forEach((emp) -> {
            if ("1".equals(emp.getGender())) {
                emp.setGender("男");
            } else {
                emp.setGender("女");
            }

            String job = emp.getJob();
            if ("1".equals(job)) {
                emp.setJob("讲师");
            } else if ("2".equals(job)) {
                emp.setJob("僵尸");
            } else if ("3".equals(job)) {
                emp.setJob("监视");
            }
        });
        return empList;
    }
}
