package com.app.springboot.controller;

import com.app.springboot.entity.Department;
import com.app.springboot.service.DepartmentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(value = "/departments")
public class DepartmentController {

    private final Logger logger =
            LoggerFactory.getLogger(DepartmentController.class);
    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/")
    public ResponseEntity<Department> saveDepartment(
            @Valid @RequestBody Department department) {
        logger.info("Inside saveDepartment of DepartmentController");
        return ResponseEntity.ok(departmentService.saveDepartment(department));
    }

    @GetMapping("/")
    public ResponseEntity<List<Department>> fetchAllDepartments() {
        logger.info("Inside saveDepartment of DepartmentController");
        return new ResponseEntity<>(departmentService.fetchAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentBasedDepartmentId(
            @PathVariable("id") Long departmentId){
        logger.info("Inside getDepartmentBasedDepartmentId of DepartmentController departmentId : {}",
                departmentId);

        return new ResponseEntity<>(departmentService.getDepartmentBasedOnDepartmentId(departmentId),
                HttpStatus.OK);
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<Map<Department,String>> deleteDepartmentByDepartmentId(
            @PathVariable("departmentId") Long departmentId) {
        logger.info("Inside deleteDepartmentByDepartmentId of DepartmentController departmentId : {}",
                departmentId);

        Map<Department, String> result = new HashMap<>();
        Department department = departmentService.deleteDepartmentByDepartmentId(departmentId);
        result.put(department, Objects.nonNull(department) ? "Department Deleted Successfully":
                "there is a problem while deleting the department with departmentId " + departmentId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<Department> updateDepartment(@PathVariable("departmentId") Long departmentId,
                                                       @RequestBody Department department) {
        logger.info("Inside updateDepartment of DepartmentController with departmentId : {} and department : {} "
                , departmentId, department);
        return new ResponseEntity<>(departmentService.updateDepartment(departmentId,department)
                , HttpStatus.OK);
    }


}
