package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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

    /**
     * 保存员工信息
     * 由于稍后，我们在保存工作经历信息的时候，需要记录是哪位员工的工作经历。
     * 所以，保存完员工信息之后，是需要获取到员工的ID的，
     * 那这里就需要通过Mybatis中提供的主键返回功能来获取。
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);
}