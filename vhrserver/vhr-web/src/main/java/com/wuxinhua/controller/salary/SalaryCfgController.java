package com.wuxinhua.controller.salary;

import com.wuxinhua.model.RespBean;
import com.wuxinhua.model.RestPageBean;
import com.wuxinhua.service.salary.SalaryCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/salary/cfg")
public class SalaryCfgController {

    @Autowired
    private SalaryCfgService salaryCfgService;

    @GetMapping("/")
    public RestPageBean getAllSalarySobCfgs(@RequestParam(value = "currentPage", defaultValue = "1") Integer currentPage, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return salaryCfgService.getAllSalarySobCfgs(currentPage, pageSize);
    }

    @PutMapping("/")
    public RespBean updateEmpSalary(@RequestParam("empId") Integer empId, @RequestParam("currentSalaryId") Integer currentSalaryId) {

        if (1 == salaryCfgService.updateEmpSalary(empId, currentSalaryId)) {
            return RespBean.ok("更新成功");
        }

        return RespBean.error("更新失败");
    }
}
