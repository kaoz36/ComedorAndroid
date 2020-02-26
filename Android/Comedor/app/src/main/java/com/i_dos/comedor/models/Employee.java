package com.i_dos.comedor.models;

public class Employee {

    private String _id;
    private String name;
    private String code_employee;
    private String department;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode_employee() {
        return code_employee;
    }

    public void setCode_employee(String code_employee) {
        this.code_employee = code_employee;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
