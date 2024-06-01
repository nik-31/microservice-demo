package com.microservice.department_service.controller;

import com.microservice.department_service.client.EmployeeClient;
import com.microservice.department_service.model.Department;
import com.microservice.department_service.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeClient employeeClient;

    @PostMapping
    public Department add(@RequestBody Department department){
        return departmentRepository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id){
        return departmentRepository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findAllWithEmployees() {
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(department ->
                department.setEmployees(
                        employeeClient.findByDepartment(department.getId())));
        return  departments;
    }
}
