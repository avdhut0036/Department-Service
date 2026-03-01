package com.app.springboot.service;

import com.app.springboot.DepartmentMockData;
import com.app.springboot.entity.Department;
import com.app.springboot.error.DepartmentNotFoundException;
import com.app.springboot.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest extends DepartmentMockData {


    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void givenValidDepartmentWhenSaveDepartmentThenDepartmentSavedSuccessfully() {

        Mockito.when(departmentRepository.save(ArgumentMatchers.any(Department.class)))
                .thenReturn(getDepartment());

        Department department = departmentService.saveDepartment(departmentTransient());

        Assertions.assertEquals(department.getDepartmentCode(),
                getDepartment().getDepartmentCode());
    }

    @Test
    void givenInvalidDepartmentWhenSaveDepartmentThenDepartmentNotSaved() {

        Department department = departmentService.
                saveDepartment(Department.builder().departmentCode("IT-003")
                .build());

        Assertions.assertNull(department);

        Mockito.verify(departmentRepository,Mockito.times(1))
                .save(ArgumentMatchers.any(Department.class));
    }

    @Test
    void givenNothingWhenFetchAllDepartmentsListThenNoException() {

        Mockito.when(departmentRepository.findAll())
                .thenReturn(departmentList());

        List<Department> departmentList =
                departmentService.fetchAllDepartments();

        Assertions.assertEquals(departmentList().size(),
                departmentList.size());

        Mockito.verify(departmentRepository, Mockito.times(1))
                .findAll();
    }

    @Test
    void givenValidDepartmentIdWhenGetDepartmentBasedOnDepartmentIdThenNoException() {

        Mockito.when(departmentRepository.findByDepartmentId(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(getDepartment()));

        Department department = departmentService.getDepartmentBasedOnDepartmentId(1L);


        Assertions.assertEquals(getDepartment().getDepartmentId(),
                department.getDepartmentId());
        Mockito.verify(departmentRepository, Mockito.times(1))
                .findByDepartmentId(ArgumentMatchers.anyLong());

    }

    @Test
    void givenInvalidDepartmentIdWhenGetDepartmentBasedOnDepartmentIdThenExceptionThrown() {
        Assertions.assertThrows(DepartmentNotFoundException.class, () ->
                departmentService.getDepartmentBasedOnDepartmentId(100L));
    }

    @Test
    void givenValidDepartmentIdWhenDeleteDepartmentByDepartmentIdThenNoExceptionRaised() {

        Mockito.when(departmentRepository.findByDepartmentId(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(getDepartment()));

        Mockito.doNothing().when(departmentRepository).deleteById(ArgumentMatchers.anyLong());

        Department department = departmentService.deleteDepartmentByDepartmentId(1L);

        Assertions.assertEquals(getDepartment().getDepartmentId(),
                department.getDepartmentId());

        Mockito.verify(departmentRepository, Mockito.times(1))
                .deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    void givenInvalidDepartmentIdWhenDeleteDepartmentBasedOnDepartmentIdThenExceptionThrown() {

        Assertions.assertThrows(DepartmentNotFoundException.class,
                () -> departmentService.deleteDepartmentByDepartmentId(200L));
    }

    @Test
    void givenValidDepartmentAndDepartmentIdWhenUpdateDepartmentThenNoExceptionUpdatedDepartment() {

        Mockito.when(departmentRepository.findByDepartmentId(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.ofNullable(getDepartment()));

        Mockito.when(departmentRepository.save(ArgumentMatchers.any(Department.class)))
                .thenReturn(updatedDepartment());

        Department updatedDept = departmentService.updateDepartment(1L,
                updatedTransientEntity());

        Assertions.assertEquals(updatedDepartment().getDepartmentName(),
                updatedDept.getDepartmentName());

        Mockito.verify(departmentRepository, Mockito.times(1))
                .save(ArgumentMatchers.any(Department.class));

    }

    @Test
    void givenInvalidDepartmentIdWhenUpdateDepartmentThenExceptionThrown() {

        Assertions.assertThrows(DepartmentNotFoundException.class,
                () -> departmentService.updateDepartment(100L,updatedTransientEntity()));
    }


}