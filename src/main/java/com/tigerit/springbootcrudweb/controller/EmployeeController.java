package com.tigerit.springbootcrudweb.controller;


import com.tigerit.springbootcrudweb.model.Employee;
import com.tigerit.springbootcrudweb.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;
    private Employee employee;
    private Model model;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String goHome(){
        return "employeesDataTable";
    }

    @GetMapping("/employees")
    public String getEmployees(Model model) {
        //assertThat(employee.getFirst_name(), notNullValue());
        //assertThat(employee.getId(), is(1L));
        List<Employee> employeesList = employeeService.getEmployeesList();
        Employee[] employeesArray = new Employee[employeesList.size()];
        employeesList.toArray(employeesArray);
        model.addAttribute("employees",employeesArray);
        return "employees";
    }
    @GetMapping("/employees/{id}")
    public String getEmployees(@PathVariable String id, Model model) {
        //assertThat(employee.getFirst_name(), notNullValue());
        //assertThat(employee.getId(), is(1L));
        List<Employee> employeesList = employeeService.getEmployeesList(id);
        Employee[] employeesArray = new Employee[employeesList.size()];
        employeesList.toArray(employeesArray);
        model.addAttribute("employees",employeesArray);
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
        employeeService.registerEmployee(employee);
        return "employeeRegistrationSuccessful";
    }
}