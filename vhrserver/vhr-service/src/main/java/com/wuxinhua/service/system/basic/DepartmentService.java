package com.wuxinhua.service.system.basic;

import com.wuxinhua.mapper.DepartmentMapper;
import com.wuxinhua.mapper.EmployeeMapper;
import com.wuxinhua.model.Department;
import com.wuxinhua.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EmployeeMapper employeeMapper;


    public List<Department> getAllDepartment() {
        // -1 总级
        return departmentMapper.getAllDepartment(-1);

    }

    public Department addDepartment(Department department) {
        //先跟怒父id 查询上级情况
        Department dep = departmentMapper.selectByPrimaryKey(department.getParentId());
        //配置添加的数据
        department.setEnabled(true);
        department.setParent(false);
        Integer i = departmentMapper.insertSelective(department);
        if (i == 1) {

            Department departmentNew = departmentMapper.getDepartmentBySelect(department.getParentId(), department.getName());
            departmentNew.setDepPath(dep.getDepPath() + "." + departmentNew.getId());
            Integer j = departmentMapper.updateByPrimaryKey(departmentNew);
            if (j == 1) {
                if (dep.getParent()) {
                    return departmentNew;
                } else {
                    dep.setParent(true);
                    if (departmentMapper.updateByPrimaryKey(dep) == 1) {
                        return departmentNew;
                    } else {
                        return null;
                    }

                }
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    public Integer deleteDepartmentById(Integer id) {

        Department department = departmentMapper.selectByPrimaryKey(id);
        if (department.getParent()) {
            return 2;
        }
        List<Employee> employees = employeeMapper.getEmpLoyees(id);
        if (employees.size() > 0) {
            return 3;
        }
        Integer i = departmentMapper.deleteByPrimaryKey(id);
        List<Department> departments = departmentMapper.getAllDepartment(department.getParentId());
        if (departments.size() > 0) {
            return i;
        } else {
            Integer j = departmentMapper.updateByid(department.getParentId(), false);
            if (j == 1) {
                return i;
            } else {
                return 4;
            }
        }
    }

    public List<Department> getAllDepartmentNew() {

        return departmentMapper.getAllDepartmentNew();

    }
}
