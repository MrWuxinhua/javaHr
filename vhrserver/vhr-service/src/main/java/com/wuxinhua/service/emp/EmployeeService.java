package com.wuxinhua.service.emp;

import com.wuxinhua.mapper.EmployeeMapper;
import com.wuxinhua.model.Employee;
import com.wuxinhua.model.RestPageBean;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {


    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public RestPageBean getAllEmpBySearch(Integer page, Integer size, Employee employee, Date[] beginAndEndDate) {

        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> employees = employeeMapper.getAllEmpBySearch(page, size, employee, beginAndEndDate);

        Long total = employeeMapper.getEmployeeTotal(employee, beginAndEndDate);

        return new RestPageBean(total, employees);
    }

    public Integer getMaxWorkId() {
        return employeeMapper.getMaxWorkId();
    }

    @Transactional
    public Boolean addEmployee(Employee employee) {
        //获取合同约定日期期限（单位年 ， 格式double）
        long beginContract = employee.getBeginContract().getTime();
        long endContract = employee.getEndContract().getTime();
        BigDecimal bigDecimal = new BigDecimal((double) ((endContract - beginContract) / (1000 * 60 * 60 * 24 * 365L)));
        employee.setContractTerm(bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

        Integer i = employeeMapper.insertSelective(employee);

        if (1 == i) {

            Employee emp = employeeMapper.getEmployeeDetaliById(employee.getId());

            rabbitTemplate.convertAndSend("wuxinhua.mail",emp);
            return true;
        }

        return false;
    }

    public Integer deleteemployeeById(Integer id) {
        return employeeMapper.deleteByPrimaryKey(id);
    }

    public Integer updateEmployeeById(Employee employee) {
        return employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public Integer addEmployeeList(List<Employee> employees) {

        return employeeMapper.addEmployeeList(employees);
    }
}
