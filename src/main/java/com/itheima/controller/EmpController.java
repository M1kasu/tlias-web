package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 员工列表查询
     *     请求路径：/emps
     *     请求方式：GET
     *     请求参数：跟随在请求路径后的参数字符串。  例：/emps?page=1&pageSize=10
     *     响应数据：json格式
     */
//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1")Integer page,
//                       @RequestParam(defaultValue = "10")Integer pageSize,
//                       String name,
//                       Integer gender,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                       @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end
//    ){
//        log.info("查询请求参数： {}, {}, {}, {}, {}, {}", page, pageSize, name, gender, begin, end);
//        PageResult<Emp> pageResult = empService.page(page, pageSize,name,gender, begin, end);
//        return Result.success(pageResult);
//    }
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("查询请求参数： {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 保存员工基本信息
     *   - 请求路径：/emps
     *   - 请求方式：POST
     *     - 请求参数：Json格式数据
     *   - 响应数据：Json格式数据
     */
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("保存员工信息：{}", emp);
        empService.save(emp);
        return Result.success();
    }

    /**
     * 员工删除
     *   - 请求路径：/emps?ids=1,2,3
     *   - 请求方式：DELETE
     *   - 请求参数：员工id
     *   - 响应数据：Json格式数据
     */

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("批量删除员工信息：ids={}", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }



}