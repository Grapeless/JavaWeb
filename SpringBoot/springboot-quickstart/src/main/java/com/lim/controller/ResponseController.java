package com.lim.controller;

import com.lim.pojo.Address;
import com.lim.pojo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResponseController {
    @RequestMapping("/plainHello")
    public Result hello() {
        return Result.success();
    }

    @RequestMapping("/getAddr")
    public Result getAddr() {
        return Result.success(new Address("hbs", "whs"));
    }

    @RequestMapping("/listAddr")
    public Result listAddr(){
        List<Address> addressList = new ArrayList<>();
        addressList.add(new Address("1","1"));
        addressList.add(new Address("2","2"));

        return Result.success(addressList);
    }
}
