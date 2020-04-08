package com.tigerit.springbootcrudweb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tigerit.springbootcrudweb.model.Employee;
import com.tigerit.springbootcrudweb.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    //adding new employee
    @RequestMapping(value = "/restemployees", method = RequestMethod.POST)
    public void addNewEmployee(@RequestBody String employeeInJson){
        ObjectMapper mapper = new ObjectMapper();
        Employee employee = null;
        try {
            employee = mapper.readValue(employeeInJson, Employee.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        employeeService.registerEmployee(employee);
    }

    //updating existing employee
    @RequestMapping(value = "/restemployees", method = RequestMethod.PUT)
    public void updateEmployee(@RequestBody String employeeInJson){

        ObjectMapper mapper = new ObjectMapper();
        Employee updateInstance = null;
        try {
            updateInstance = mapper.readValue(employeeInJson, Employee.class);
            System.out.println(updateInstance.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(updateInstance!=null)
                employeeService.updateEmployee(updateInstance);
        else System.out.println("updateInstance is null");
    }

    //deleting existing employee
    @RequestMapping(value = "/restemployees", method = RequestMethod.DELETE)
    public void deleteEmployeeById(@RequestBody String employeeInJson){
        ObjectMapper mapper = new ObjectMapper();
        Employee deleteInstance = null;
        try {
            deleteInstance = mapper.readValue(employeeInJson, Employee.class);
            System.out.println(deleteInstance.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(deleteInstance!=null)
            employeeService.deleteEmployee(deleteInstance);
        else System.out.println("deleteInstance is null");
    }


}