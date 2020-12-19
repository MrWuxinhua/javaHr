package com.wuxinhua.service.salary;

import com.wuxinhua.mapper.EmpSalaryMapper;
import com.wuxinhua.mapper.SalaryMapper;
import com.wuxinhua.model.EmpSalary;
import com.wuxinhua.model.RestPageBean;
import com.wuxinhua.model.SalarySobCfg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryCfgService {
    @Autowired
    private SalaryMapper salaryMapper;

    @Autowired
    private EmpSalaryMapper empSalaryMapper;

    public RestPageBean getAllSalarySobCfgs(Integer currentPage, Integer pageSize) {
        if (currentPage != null && pageSize != null) {
            currentPage = (currentPage - 1) * pageSize;
        }
        //分页查询
        List<SalarySobCfg> salarySobCfgList = salaryMapper.getAllSalarySobCfgs(currentPage, pageSize);
        //总数
        Long total = salaryMapper.getSalarySobCfgListTotal();


        return new RestPageBean(total, salarySobCfgList);
    }

    public Integer updateEmpSalary(Integer empId, Integer currentSalaryId) {

        //先根据员工id 查询出中间表
        EmpSalary empSalary = empSalaryMapper.getEmpSalaryByEmpId(empId);

        if (empSalary == null) {
            //添加
            EmpSalary es = new EmpSalary();
            es.setEid(empId);
            es.setSid(currentSalaryId);
            return empSalaryMapper.insert(es);
        }
        empSalary.setSid(currentSalaryId);
        return empSalaryMapper.updateByPrimaryKeySelective(empSalary);


    }
}
