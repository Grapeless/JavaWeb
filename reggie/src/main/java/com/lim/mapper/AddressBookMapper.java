package com.lim.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lim.pojo.AddressBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
