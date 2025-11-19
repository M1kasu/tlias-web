package com.itheima.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    /**
     * 注解：@Transactional
     * 作用：就是在当前这个方法执行开始之前来开启事务，方法执行完毕之后提交事务。
     *      如果在这个方法执行的过程当中出现了异常，就会进行事务的回滚操作。
     * 位置：业务层的方法上、类上、接口上
     *   - 方法上：当前方法交给spring进行事务管理
     *   - 类上：当前类中所有的方法都交由spring进行事务管理
     *   - 接口上：接口下所有的实现类当中所有的方法都交给spring 进行事务管理
     *
     *      rollbackFor
     *   默认情况下，只有出现RuntimeException(运行时异常)才会回滚事务。
     *   假如我们想让所有的异常都回滚，需要来配置@Transactional注解当中的rollbackFor属性，
     *   通过rollbackFor这个属性可以指定出现何种异常类型回滚事务。
     *
     *   propagation
     *   propagation属性：事务传播行为
     *   1. REQUIRED（默认值）
     *   2. REQUIRES_NEW
     */
//    @Transactional(rollbackFor = Exception.class)
//    @Override
//    public void save(Emp emp) {
//        //1、补全属性
//        emp.setCreateTime(LocalDateTime.now());
//        emp.setUpdateTime(LocalDateTime.now());
//
//        //2.保存员工基本信息
//        empMapper.insert(emp);
//
//        //int i = 1/0;
//
////        //模拟：异常发生
////        if(true){
////            throw new Exception("出异常啦....");
////        }
//
//        //3. 保存员工的工作经历信息 - 批量 (稍后完成)
//        Integer empId = emp.getId();//@Options注解应该就是为了这句代码可以获取到数据库生成的id
//        List<EmpExpr> exprList = emp.getExprList();
//        if(!CollectionUtils.isEmpty(exprList)){
//            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
//            empExprMapper.insertBatch(exprList);
//        }
//    }



    @Autowired
    private EmpLogService empLogService;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        try {
            //1.补全基础属性
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());

            //2.保存员工基本信息
            empMapper.insert(emp);

//            int i = 1/0;

            //3. 保存员工的工作经历信息 - 批量
            Integer empId = emp.getId();
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(empExpr -> empExpr.setEmpId(empId));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogService.insertLog(empLog);
        }
    }


    }