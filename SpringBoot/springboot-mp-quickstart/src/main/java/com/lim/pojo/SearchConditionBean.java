package com.lim.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchConditionBean {
    private String name;
    private Short gender;
    private LocalDate begin;
    private LocalDate end;
    private Integer page = 1;
    private Integer pageSize = 10;
}
