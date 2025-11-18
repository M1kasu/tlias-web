package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理控制器
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门列表 GET http://localhost:8080/depts
     */
    @GetMapping
    public Result list(){
        log.info("查询部门列表");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     * 删除部门 delete http://localhost:8080/depts?id=1
     */
    @DeleteMapping
    public Result delete(Integer id){ //如果请求参数名与形参变量名相同，直接定义方法形参即可接收。（省略@RequestParam）
//        System.out.println("根据id删除部门, id=" + id);
        log.info("根据id删除部门, id: {}" , id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门 POST http://localhost:8080/depts
     * - JSON格式的参数，通常会使用一个实体对象进行接收 。
     * - 规则：JSON数据的键名与方法形参对象的属性名相同，并需要使用@RequestBody注解标识。
     */
    @PostMapping
    public Result add(@RequestBody Dept dept){
//        System.out.println("新增部门, dept=" + dept);
        log.info("新增部门, dept: {}" , dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据ID查询部门 GET http://localhost:8080/depts/1
     * 路径参数：通过请求URL直接传递参数，使用{…}来标识该路径参数，需要使用 @PathVariable获取路径参数。
     * 如果路径参数名与controller方法形参名称一致，@PathVariable注解的value属性是可以省略的。
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id){
//        System.out.println("查询部门, id=" + id);
        log.info("根据ID查询, id: {}" , id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门 PUT http://localhost:8080/depts
     * - JSON格式的参数，通常会使用一个实体对象进行接收 。
     * - 规则：JSON数据的键名与方法形参对象的属性名相同，并需要使用@RequestBody注解标识。
     */
    @PutMapping
    public Result update(@RequestBody Dept dept){
//        System.out.println("修改部门, dept=" + dept);
        log.info("修改部门, dept: {}" , dept);
        deptService.update(dept);
        return Result.success();
    }
}