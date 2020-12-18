package com.wuxinhua.service.salary;

import com.wuxinhua.mapper.SalaryMapper;
import com.wuxinhua.model.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SalaryService {

    @Autowired
    private SalaryMapper salaryMapper;


    public List<Salary> getAllSalary() {
        return salaryMapper.getAllSalary();
    }

    public Integer updateSalary(Salary salary) {
        return salaryMapper.updateByPrimaryKeySelective(salary);
    }

    public Integer deleteSalaryById(Integer id) {
        return salaryMapper.deleteByPrimaryKey(id);
    }

    public Integer addSalarySob(Salary salary) {
        salary.setCreateDate(new Date());
        return salaryMapper.insertSelective(salary);
    }
}
