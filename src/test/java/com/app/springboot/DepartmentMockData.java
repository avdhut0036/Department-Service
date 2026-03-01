package com.app.springboot;

import com.app.springboot.entity.Department;

import java.util.List;

public class DepartmentMockData {



    public Department getDepartment() {
        return Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentAddress("Banglore")
                .departmentCode("IT-09")
                .build();
    }

    public Department departmentTransient() {
        return Department.builder()
                .departmentName("IT")
                .departmentAddress("Banglore")
                .departmentCode("IT-09").build();
    }

    public List<Department> departmentList() {

        return List.of(getDepartment(),
                Department.builder()
                        .departmentId(2L)
                        .departmentName("CE")
                        .departmentAddress("Chennai")
                        .departmentCode("CE-01")
                        .build());
    }

    public Department updatedDepartment() {

        return Department.builder()
                .departmentId(1L)
                .departmentName("Information Technology")
                .departmentCode("IT-001")
                .departmentAddress("Banglore")
                .build();
    }

    public Department updatedTransientEntity() {
        return Department.builder()
                .departmentName("Information Technology")
                .departmentCode("IT-001")
                .build();
    }

}
