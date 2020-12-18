package com.wuxinhua.controller.salary;

import com.wuxinhua.model.RespBean;
import com.wuxinhua.model.Salary;
import com.wuxinhua.service.salary.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Service;
import java.util.List;

@RestController
@RequestMapping("/salary/sob")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;


    @GetMapping("/")
    public List<Salary> getAllSalary() {
        return salaryService.getAllSalary();
    }

    @PutMapping("/")
    public RespBean updateSalary(@RequestBody Salary salary) {
        if (1 == salaryService.updateSalary(salary)) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteSalaryById(@PathVariable(value = "id", required = true) Integer id) {
        if (1 == salaryService.deleteSalaryById(id)) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @PostMapping("/")
    public RespBean addSalarySob(@RequestBody Salary salary) {
        if (1 == salaryService.addSalarySob(salary)) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

}
