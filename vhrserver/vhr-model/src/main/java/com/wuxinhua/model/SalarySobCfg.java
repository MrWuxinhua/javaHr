package com.wuxinhua.model;


/**
 * 员工工资套账
 */
public class SalarySobCfg {

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 工号
     */
    private String workID;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 部门
     */
    private Department department;
    /**
     * 工资账套
     */
    private Salary salary;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorkID() {
        return workID;
    }

    public void setWorkID(String workID) {
        this.workID = workID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "SalarySobCfg{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", workID='" + workID + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }
}
