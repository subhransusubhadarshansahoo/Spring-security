package com.subhransu.rest_crud_demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {


    //define all the fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //  @Column(name="email")   no error but you will get wrong value
    private String firstName;
    private String lastName;
    //  @Column(name = "first_name")
    private String email;


    //perform some mistake intentionally
    //wrong naming without the column attribute
    //2nd mistake add different column value to different field
    //do not define the no argument constructor

    public Employee() {

    }

    public Employee(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
