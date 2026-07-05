package com.subhransu.rest_crud_demo.jpaRepository;

import com.subhransu.rest_crud_demo.entity.Employee;
import com.subhransu.rest_crud_demo.projection.EmployeeProjection;
import org.hibernate.annotations.processing.Find;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository  extends JpaRepository<Employee,Integer> {

   public List<EmployeeProjection> findByFirstName(String name);

//    public Employee findByFirstName(String name);


    public List<Employee> findDistinctByFirstNameAndLastName(String firstName,String LastName);



    ///you can also write your custom query

    @Query("select e from Employee e where e.firstName=:name")
    public List<Employee> findByName(@Param("name") String name);

}
