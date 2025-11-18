package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
////        //1. 获取总记录数
////        Long total = empMapper.count();
////
////        //2. 获取结果列表
////        Integer start = (page - 1) * pageSize;
////        List<Emp> empList = empMapper.list(start, pageSize);
////
////        //3. 封装结果
////        return new PageResult(total, empList);
//
//        //1. 设置分页参数
//        PageHelper.startPage(page,pageSize);
//
//        //2. 执行查询
//        List<Emp> empList = empMapper.list(name, gender, begin, end);
//        Page<Emp> p = (Page<Emp>) empList;
//
//        //3. 封装结果
//        return new PageResult<Emp>(p.getTotal(), p.getResult());
//    }


    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        Integer page = empQueryParam.getPage();
        Integer pageSize = empQueryParam.getPageSize();
        //1. 设置分页参数
        PageHelper.startPage(page,pageSize);

        //2. 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) empList;

        //3. 封装结果
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }
}