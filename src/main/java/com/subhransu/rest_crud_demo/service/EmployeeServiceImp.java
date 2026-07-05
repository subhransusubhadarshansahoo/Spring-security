package com.subhransu.rest_crud_demo.service;


import com.subhransu.rest_crud_demo.dto.EmployeeDto;
import com.subhransu.rest_crud_demo.entity.Employee;
import com.subhransu.rest_crud_demo.jpaRepository.EmployeeRepository;
import com.subhransu.rest_crud_demo.projection.EmployeeProjection;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImp  implements  EmployeeService{

    private EmployeeRepository employeeRepository;

@Autowired
public EmployeeServiceImp(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeServiceImp() {

    }

//    @Override
//    public List<Employee> getAllEmployees() {
//    //in industry where database contains millions of records instead of
//    //    returning  all records use pagination which will be more efficient approach
//
//    return  employeeRepository.findAll();
//    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {


    return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee getBYId  (int empId) {

       Employee emp=employeeRepository.findById(empId)
               .orElseThrow(()->new RuntimeException("Employee is not found"));





       return  emp;




    }

    @Override
    public  Employee save(Employee employee) {


        Employee employee1 = employeeRepository.save(employee);
        return     employee1;
    }

    @Override
    public Employee updateEmployee(Employee employee) {

    return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployeeFields(Employee employee) {


     return employeeRepository.save(employee);
    }


@Override
    public Page<Employee> getEmployeeByPaging(int pageNo,int pageSize){


    Page<Employee> all = employeeRepository.findAll(PageRequest.of(pageNo, pageSize));


return  all;
}

    @Override
    public List<EmployeeProjection>  searchByName(String name) {
       return employeeRepository.findByFirstName(name);
    }

    @Override
    public List<Employee> findDistinctEmployee(String fName, String lastName) {
      return employeeRepository.findDistinctByFirstNameAndLastName(fName, lastName);
    }

    @Override
    public void DeleteEmployee(int id) {
    employeeRepository.deleteById(id);

    }


//    public Employee searchByName(String name) {
//        return employeeRepository.findByFirstName(name);  throw exception
//    }










}
