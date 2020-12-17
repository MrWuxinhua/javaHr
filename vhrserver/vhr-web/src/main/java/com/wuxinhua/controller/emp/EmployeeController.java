package com.wuxinhua.controller.emp;

import com.wuxinhua.model.*;
import com.wuxinhua.service.PoliticsstatusService;
import com.wuxinhua.service.emp.EmployeeService;
import com.wuxinhua.service.emp.NationServie;
import com.wuxinhua.service.system.basic.DepartmentService;
import com.wuxinhua.service.system.basic.JobLevelService;
import com.wuxinhua.service.system.basic.PositionService;
import com.wuxinhua.service.utils.POIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/employee/basic")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JobLevelService jobLevelService;

    //民族
    @Autowired
    private NationServie nationServie;

    //政治面貌
    @Autowired
    private PoliticsstatusService politicsstatusService;

    //职位
    @Autowired
    private PositionService positionService;

    @Autowired
    private DepartmentService departmentService;


    @GetMapping("/")
    public RestPageBean getAllEmpBySearch(@RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , Employee employee , @RequestParam(value = "beginAndEndDate" , required = false) Date[] beginAndEndDate) {

        return employeeService.getAllEmpBySearch(page, size, employee , beginAndEndDate);

    }


    @GetMapping("/jobleves")
    public List<JobLevel> getAllJobLeve() {
        return jobLevelService.getJobLevels();
    }


    //民族
    @GetMapping("/nations")
    public List<Nation> getAllNation() {
        return nationServie.getAllNation();
    }

    //政治面貌
    @GetMapping("/politicsstatuss")
    public List<Politicsstatus> getAllPoliticsstatus() {
        return politicsstatusService.getAllPoliticsstatus();
    }

    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.getAllPosition();
    }

    @GetMapping("/departments")
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartment();
    }

    @GetMapping("/maxWorkId")
    public RespBean getMaxWorkId() {

        RespBean respBean = RespBean.build().setStatus(200).setObj(String.format("%08d", employeeService.getMaxWorkId() + 1));
        return respBean;

    }

    @PostMapping("/")
    public RespBean addEmployee(@RequestBody Employee employee) {
        if (employeeService.addEmployee(employee)) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }


    @DeleteMapping("/")
    public RespBean deleteemployeeById(@RequestParam(value = "id", required = true) Integer id) {
        if (1 == employeeService.deleteemployeeById(id)) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    @PutMapping("/")
    public RespBean updateEmployeeById(@RequestBody Employee employee) {
        if (1 == employeeService.updateEmployeeById(employee)) {
            return RespBean.ok("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportExcel(@RequestParam(value = "keyWords", required = false) String keyWords) {
        Employee employee = new Employee();
        employee.setName(keyWords);

        RestPageBean response = this.getAllEmpBySearch(null, null, employee,null);
        return POIUtil.exportExcel((List<Employee>) response.getData());
    }

    @PostMapping("/import")
    public RespBean inportExcel(MultipartFile file) throws IOException {

        List<Department> departments = departmentService.getAllDepartmentNew();

        List<Employee> employees = POIUtil.importExcel(file, nationServie.getAllNation(), politicsstatusService.getAllPoliticsstatus(), departments, positionService.getAllPosition(), jobLevelService.getJobLevels());

        if (employees.size() == employeeService.addEmployeeList(employees)) {
            return RespBean.ok("导入成功");
        }
        return RespBean.error("导入失败");
    }

}
