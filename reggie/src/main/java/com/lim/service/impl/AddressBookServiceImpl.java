package com.lim.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lim.mapper.AddressBookMapper;
import com.lim.pojo.AddressBook;
import com.lim.service.AddressBookService;
import org.springframework.stereotype.Service;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook>
                                    implements AddressBookService  {
}
