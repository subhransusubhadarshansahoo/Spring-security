package com.subhransu.rest_crud_demo.rest;


import com.subhransu.rest_crud_demo.dto.EmployeeDto;
import com.subhransu.rest_crud_demo.entity.Employee;
import com.subhransu.rest_crud_demo.projection.EmployeeProjection;
import com.subhransu.rest_crud_demo.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class EmployeeRest {

    private EmployeeService employeeService;

    public EmployeeRest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    @GetMapping("/employees")
//    public List<Employee> getAll() {
//        return employeeService.getAllEmployees();
//
//    }


    @GetMapping("/employees")
    public Page<Employee> getAll(Pageable pageable){
        return  employeeService.getAllEmployees(pageable);


        // it  contains both pagination and sorting property
    }



    @GetMapping("/employees/{empId}")
    public Employee getById(@PathVariable int empId) {

        return employeeService.getBYId(empId);
    }


    @PostMapping("/employees")
    public Employee insert(@RequestBody Employee employee) {

        //requestBody is used to take the json from request body and convert it into java object


        return employeeService.save(employee);
    }


    @PutMapping("/employees/{empId}")
    public Employee updateEmployee(@PathVariable int empId, @RequestBody Employee employee) {


        Employee byId = employeeService.getBYId(empId);


        System.out.println("comparing employee object with byId object " + (byId == employee));


//        byId.setId(empId);  it will create a new employee

//          System.out.println(employee.getId());
//
         employee.setId(empId);


        return employeeService.updateEmployee(employee);


    }


    @PatchMapping("/employees/{empId}")
    public Employee updateEmployee(@PathVariable int empId, @RequestBody EmployeeDto employeeDto) {
        Employee employee = employeeService.getBYId(empId);
// it is detached object

        if (employeeDto.getEmail() != null) {
            employee.setEmail(employeeDto.getEmail());
        }
        if (employeeDto.getFirstName() != null) {
            employee.setFirstName(employeeDto.getFirstName());
        }
        if (employeeDto.getLastName() != null) {
            employee.setLastName(employeeDto.getLastName());
        }


        return employeeService.updateEmployeeFields(employee);


    }

//    @GetMapping(value = "/employees", params = {"pageNo", "pageSize"})
//    //here params means both the values are necessary
//    public Page<Employee> getByPaging
//            (@RequestParam(defaultValue = "0") int pageNo,
//             @RequestParam(defaultValue = "10") int pageSize) {
//
//
//        return employeeService.getEmployeeByPaging(pageNo, pageSize);
//
//
//    }


    @GetMapping("/employees/search")
    public List<EmployeeProjection>  searchByName(@RequestParam String firstName) {


        return  employeeService.searchByName(firstName);
    }


    @GetMapping("/employees/search2")
    public List<Employee> searchByName(@RequestParam String firstName
            ,@RequestParam String lastName) {


        return  employeeService.findDistinctEmployee(firstName,lastName);
    }


//    @GetMapping("/employees/search")
//    public  Employee searchByName(@RequestParam String firstName) {
//
//
//        return  employeeService.searchByName(firstName);
//    }  it will throw an exception due to multiple records



    @DeleteMapping("/employees/{id}")
    public void delete(@PathVariable int id ){

   employeeService.DeleteEmployee(id);

    }






}
