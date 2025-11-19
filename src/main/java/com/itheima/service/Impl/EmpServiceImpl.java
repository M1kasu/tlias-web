package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工管理
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

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

    @Override
    public void insert(Emp emp) {
        //1、补全属性
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());

        //2.保存员工基本信息
        empMapper.insert(emp);

        //3. 保存员工的工作经历信息 - 批量 (稍后完成)
        Integer empId = emp.getId();//@Options注解应该就是为了这句代码可以获取到数据库生成的id
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }


    }
}