package com.wuxinhua.controller.system.basic;

import com.wuxinhua.model.Department;
import com.wuxinhua.model.RespBean;
import com.wuxinhua.service.system.basic.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门
 */
@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/")
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }


    @PostMapping("/")
    public RespBean addDepartment(@RequestBody Department department){
        Department departmentNem = departmentService.addDepartment(department);

        if(departmentNem == null ){
            return RespBean.error("添加失败");
        }
        return RespBean.ok("添加成功" , departmentNem);

    }


    @DeleteMapping("/{id}")
    public RespBean deleteDepartmentById(@PathVariable("id") Integer id){

        Integer result =  departmentService.deleteDepartmentById(id);

        if(result == 1){
            return RespBean.ok("删除成功");
        }else if(result == 2){
            return RespBean.error("删除失败 ， 存在子级部门");
        }else if(result == 3){
            return RespBean.error("删除失败，当前部门存在员工信息");
        }else {
            return RespBean.error("删除失败");
        }



    }
}
