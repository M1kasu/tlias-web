package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理控制器
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门列表 GET http://localhost:8080/depts
     */
    @GetMapping("/depts")
    public Result list(){
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 删除部门 delete http://localhost:8080/depts?id=1
     */
    @DeleteMapping("/depts")
    public Result delete(Integer id){ //如果请求参数名与形参变量名相同，直接定义方法形参即可接收。（省略@RequestParam）
        System.out.println("根据id删除部门, id=" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门 POST http://localhost:8080/depts
     * - JSON格式的参数，通常会使用一个实体对象进行接收 。
     * - 规则：JSON数据的键名与方法形参对象的属性名相同，并需要使用@RequestBody注解标识。
     */
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        System.out.println("新增部门, dept=" + dept);
        deptService.add(dept);
        return Result.success();
    }


}