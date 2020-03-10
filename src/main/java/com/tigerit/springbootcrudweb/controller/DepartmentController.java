package com.tigerit.springbootcrudweb.controller;

import com.tigerit.springbootcrudweb.model.Department;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Controller
public class DepartmentController {

    @GetMapping("/departments")
    public String getDepartments(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        String urlGETList = "http://localhost:8081/dept/find-all";
        ResponseEntity<Department[]> response = restTemplate.getForEntity(urlGETList, Department[].class);
        Department[] departments = response.getBody();
        //assertThat(department.getFirst_name(), notNullValue());
        //assertThat(department.getId(), is(1L));
        model.addAttribute("departments",departments);
        return "departments";
    }
    @GetMapping("/departments/{id}")
    public String getDepartments(@PathVariable String id, Model model) {
        model.addAttribute("ide", id);
        RestTemplate restTemplate = new RestTemplate();
        String urlGETList = "http://localhost:8081/dept/find-by-id/"+id;

        ResponseEntity<Department> response = restTemplate.getForEntity(urlGETList, Department.class);
        Department department = response.getBody();
        //assertThat(department.getFirst_name(), notNullValue());
        //assertThat(department.getId(), is(1L));
        ArrayList<Department> departments=new ArrayList<Department>();
        departments.add(department);
        model.addAttribute("departments",departments);
        return "departments";
    }

}