package com.app.springboot.repository;

import com.app.springboot.DepartmentMockData;
import com.app.springboot.entity.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.Optional;

@DataJpaTest
class DepartmentRepositoryTest extends DepartmentMockData {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DepartmentRepository departmentRepository;



    @BeforeEach
    void setUp() {

        entityManager.persist(departmentTransient());

    }

    @Test
    void givenValidDepartmentIdWhenFindByDepartmentIdThenNoException() {
        Optional<Department> department = departmentRepository.findByDepartmentId(1L);
        Assertions.assertNotNull(department);
    }

    @Test
    void givenInvalidDepartmentIdWhenFindByDepartmentIdThenNoDepartmentFound() {

        Optional<Department> byDepartmentId = departmentRepository.findByDepartmentId(100L);

        boolean empty = byDepartmentId.isEmpty();

        Assertions.assertTrue(empty);
    }

}