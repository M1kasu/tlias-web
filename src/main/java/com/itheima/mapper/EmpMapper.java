package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

    /**
     * 查询总记录数
     */
    @Select("select count(*) from emp")
    Long count();


    /**
     * 查询所有的员工及其对应的部门名称
     * - PageHelper实现分页查询时，SQL语句的结尾一定一定一定不要加分号(;).。
     * - PageHelper只会对紧跟在其后的第一条SQL语句进行分页处理。
     */
//    @Select("select e.*, d.name as deptName from emp e left join dept d on e.dept_id = d.id limit #{start}, #{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);
//    @Select("select e.*, d.name as deptName from emp e left join dept d on e.dept_id = d.id")

    /**
     * 查询所有的员工及其对应的部门名称
     */
    //public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    public List<Emp> list(EmpQueryParam empQueryParam);
}