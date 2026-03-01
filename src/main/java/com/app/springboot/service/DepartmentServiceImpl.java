package com.app.springboot.service;

import com.app.springboot.entity.Department;
import com.app.springboot.error.DepartmentNotFoundException;
import com.app.springboot.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final Logger logger =
            LoggerFactory.getLogger(DepartmentServiceImpl.class);


    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department saveDepartment(Department department) {
        logger.info("Inside saveDepartment method of DepartmentServiceImpl");
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchAllDepartments() {
        logger.info("Inside fetchAllDepartments method of DepartmentServiceImpl");
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentBasedOnDepartmentId(Long departmentId) {
        logger.info("Inside getDepartmentBasedOnDepartmentId method of DepartmentServiceImpl " +
                "departmentId : {}", departmentId);
        Optional<Department> department = departmentRepository.findByDepartmentId(departmentId);
        if(department.isEmpty()) {
            throw new DepartmentNotFoundException("Department Not Available.");
        }

        return department.get();
    }

    @Override
    public Department deleteDepartmentByDepartmentId(Long departmentId) {
        logger.info("Inside deleteDepartmentByDepartmentId method of DepartmentServiceImpl " +
                "departmentId : {}", departmentId);
        Department depDB = getDepartmentBasedOnDepartmentId(departmentId);

        if(Objects.nonNull(depDB)) {
            departmentRepository.deleteById(departmentId);
        }
        return depDB;
    }

    @Override
    public Department updateDepartment(Long departmentId, Department update) {
        logger.info("Inside updateDepartment method of DepartmentServiceImpl with " +
                "departmentId : {} and updatedDepartment : {}" , departmentId, update);
        Department depDB = getDepartmentBasedOnDepartmentId(departmentId);

        if(Objects.nonNull(update.getDepartmentName()) &&
                !"".equalsIgnoreCase(update.getDepartmentName())) {
            depDB.setDepartmentName(update.getDepartmentName());
        }

        if(Objects.nonNull(update.getDepartmentAddress()) &&
        !"".equalsIgnoreCase(update.getDepartmentAddress())) {
            depDB.setDepartmentAddress(update.getDepartmentAddress());
        }

        if(Objects.nonNull(update.getDepartmentCode()) &&
        !"".equalsIgnoreCase(update.getDepartmentCode())){
            depDB.setDepartmentCode(update.getDepartmentCode());
        }

        return departmentRepository.save(depDB);
    }
}
