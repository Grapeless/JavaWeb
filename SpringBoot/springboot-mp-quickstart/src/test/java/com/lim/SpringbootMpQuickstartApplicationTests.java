package com.lim;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lim.mapper.EmpMapper;
import com.lim.pojo.Emp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SpringbootMpQuickstartApplicationTests {

    @Autowired
    private EmpMapper empMapper;
    @Test
    void testPagingQuery(){
        IPage<Emp> iPage = new Page<Emp>(1,2);

        empMapper.selectPage(iPage,null);
        log.info("iPage.getCurrent()：{}",iPage.getCurrent());
        iPage.getRecords();
    }

}
