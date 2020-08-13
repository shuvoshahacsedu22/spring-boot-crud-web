package com.tigerit.springbootcrudweb.controller;


import com.tigerit.springbootcrudweb.model.Employee;
import com.tigerit.springbootcrudweb.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("")
public class EmployeeController {
    private final EmployeeService employeeService;
    private Employee employee;
    private Model model;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String goHome()
    {
        return "index";

    }

    @GetMapping("/employees")
    public String getEmployees(Model model) {
        //assertThat(employee.getFirst_name(), notNullValue());
        //assertThat(employee.getId(), is(1L));
        /** As data table itself filling in the table so we don't etrive data for table
         *
         List<Employee> employeesList = employeeService.getEmployeesList();
         Employee[] employeesArray = new Employee[employeesList.size()];
         employeesList.toArray(employeesArray);
         model.addAttribute("employees",employeesArray);
         *
         * */
        return "employees";
    }

    @GetMapping("/employeeRegistrationForm")
    public String registerEmployee(Model model) {
        model.addAttribute("employee",new Employee());
        return "employeeRegistrationForm";
    }

    @GetMapping("/employeeUpdateForm")
    public String updateEmployee(Model model) {
        model.addAttribute("employee",new Employee());
        return "employeeUpdateForm";
    }
    @PostMapping("/employeeUpdateForm")
    public String updateEmployee(@ModelAttribute("employee") Employee employee, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "error";
        }
        System.out.println(employee.toString());
        employeeService.updateEmployee(employee);
        return "employeeUpdateSuccessful";
    }


    @GetMapping("/employeeDeleteForm")
    public String deleteEmployee(Model model) {
        model.addAttribute("employee",new Employee());
        return "employeeDeleteForm";
    }
    @PostMapping("/employeeDeleteForm")
    public String deleteEmployee(@ModelAttribute("employee") Employee employee, BindingResult result, ModelMap mode) {
        employeeService.deleteEmployee(employee);
        return "employeeDeleteSuccessful";
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