package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpMapper {
    /**
     * 查询所有的员工及其对应的部门名称
     */
    @Select("select e.*, d.name as deptName from emp e left join dept d on e.dept_id = d.id")
    public List<Emp> list();

}