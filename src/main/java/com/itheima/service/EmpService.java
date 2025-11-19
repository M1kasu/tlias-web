package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;

import java.time.LocalDate;

public interface EmpService {
//    /**
//     * 员工列表分页查询
//     *
//     * @param page     当前页码
//     * @param pageSize 每页显示记录数
//     * @param name
//     * @param gender
//     * @param begin
//     * @param end
//     * @return
//     */
    //PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     *  员工列表分页查询
     * @param empQueryParam
     * @return
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 保存员工基本信息
     * @param emp
     */
    void insert(Emp emp);
}