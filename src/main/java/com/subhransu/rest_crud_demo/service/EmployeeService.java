package com.subhransu.rest_crud_demo.service;

import com.subhransu.rest_crud_demo.dto.EmployeeDto;
import com.subhransu.rest_crud_demo.entity.Employee;
import com.subhransu.rest_crud_demo.projection.EmployeeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface EmployeeService {


    public Page<Employee> getAllEmployees(Pageable pageable);


    //get employee by id
    public  Employee getBYId(int empId);

// adding new employee
    public  Employee save(Employee employee);



    public  Employee updateEmployee(Employee employee);



    // patch to update specific part

    public Employee updateEmployeeFields(Employee employee);



    public Page<Employee> getEmployeeByPaging(int pageNo,int pageSize);


   public List<EmployeeProjection>  searchByName(String name);


    public  List<Employee>  findDistinctEmployee(String fName,String LastName);


    public  void DeleteEmployee(int id);




}
