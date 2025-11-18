package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门
     */
    @Select("select * from dept")
    public List<Dept> findAll();

    /**
     * 根据id删除部门
     */
    @Select("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增部门
     */
    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void add(Dept dept);


    /**
     * 根据id查询部门
     */
    @Select("select * from dept where id = #{id}")
    Dept getById(Integer id);


    /**
     * 修改部门
     */
    @Insert("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}