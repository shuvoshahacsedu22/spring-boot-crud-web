package com.tigerit.springbootcrudweb.controller;


import com.tigerit.springbootcrudweb.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Controller
public class EmployeeController {

    private Employee employee;
    private Model model;

    @GetMapping("/employees")
    public String getEmployees(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String urlGETList = "http://localhost:8081/emp/find-all";
        ResponseEntity<Employee[]> response = restTemplate.getForEntity(urlGETList, Employee[].class);
        Employee[] employees = response.getBody();
        //assertThat(employee.getFirst_name(), notNullValue());
        //assertThat(employee.getId(), is(1L));
        model.addAttribute("employees",employees);
        return "employees";
    }
    @GetMapping("/employees/{id}")
    public String getEmployees(@PathVariable String id, Model model) {
        model.addAttribute("ide", id);
        RestTemplate restTemplate = new RestTemplate();
        String urlGETList = "http://localhost:8081/emp/find-by-id/"+id;

        ResponseEntity<Employee> response = restTemplate.getForEntity(urlGETList, Employee.class);
        Employee employee = response.getBody();
        //assertThat(employee.getFirst_name(), notNullValue());
        //assertThat(employee.getId(), is(1L));
        ArrayList<Employee> employees=new ArrayList<Employee>();
        employees.add(employee);

        model.addAttribute("employees",employees);
        return "employees";
    }
    @GetMapping("/employeeRegistrationForm")
    public String registerEmployee(Model model) {
        model.addAttribute("employee",new Employee());
        return "employeeRegistrationForm";
    }
    @PostMapping("/employeeRegistrationForm")
    public String registerEmployee(@ModelAttribute("employee") Employee employee, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        System.out.println(employee.toString());
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.postForObject("http://localhost:8081/emp/save", employee,Employee.class);
        return "employeeRegistrationSuccessful";
    }
}