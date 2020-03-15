package com.tigerit.springbootcrudweb.controller;

import com.tigerit.springbootcrudweb.model.Employee;
import com.tigerit.springbootcrudweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(path="/restemployees", method= RequestMethod.GET)
    public List<Employee> getAllEmployees(){
        return employeeService.getEmployeesList();
    }
    @RequestMapping(value = "/restemployees/{id}", method = RequestMethod.GET)
    public List<Employee> getEmployeeById(@PathVariable("id") String id){
        return employeeService.getEmployeesList(id);
    }

}