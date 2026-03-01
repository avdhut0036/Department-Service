package com.app.springboot.service;

import com.app.springboot.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department saveDepartment(Department department);

    List<Department> fetchAllDepartments();

    Department getDepartmentBasedOnDepartmentId(Long departmentId);

    Department deleteDepartmentByDepartmentId(Long departmentId);

    Department updateDepartment(Long departmentId, Department update);
}
