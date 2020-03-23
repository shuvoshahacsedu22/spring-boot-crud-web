package com.tigerit.springbootcrudweb.service;

import com.tigerit.springbootcrudweb.model.Employee;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService {

    //calls api to retrieve list of pojo object
    public List<Employee> getEmployeesList(){
        RestTemplate restTemplate = new RestTemplate();
        String urlGETList = "http://localhost:8081/emp/find-all";
        ResponseEntity<Employee[]> response = restTemplate.getForEntity(urlGETList, Employee[].class);
        Employee[] employees = response.getBody();
        //return employees; //returns array of object instead of list
        return  Arrays.asList(employees);//returns list instead of arrays
    }
    //calls api to retrieve list of pojo object with given id of the object(Employee)
    //overloaded method
    public List<Employee> getEmployeesList(String id) {
        RestTemplate restTemplate = new RestTemplate();
        String urlGETList = "http://localhost:8081/emp/find-by-id/"+id;
        ResponseEntity<Employee> response = restTemplate.getForEntity(urlGETList, Employee.class);
        Employee employee = response.getBody();
        //assertThat(employee.getFirst_name(), notNullValue());
        //assertThat(employee.getId(), is(1L));
        ArrayList<Employee> employees=new ArrayList<Employee>();
        employees.add(employee);
        return employees;
    }
    //register new employee to database with save api which takes all the field excluding id of the employee
    public void registerEmployee(Employee employee){
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.postForObject("http://localhost:8081/emp/save", employee,Employee.class);
    }

    //register new employee to database with update-by-id api which takes all the field including id of the employee
    public void updateEmployee(Employee updatedInstance){
        RestTemplate restTemplate = new RestTemplate();
        String employeeResourceUrl="http://localhost:8081/emp/update-by-id/" +updatedInstance.getEmployeeId().toString();
        restTemplate.put(employeeResourceUrl,updatedInstance);
    }
    public void deleteEmployee(Employee deleteInstance){
        RestTemplate restTemplate = new RestTemplate();
        String employeeResourceUrl = "http://localhost:8081/emp/delete-by-id/" +deleteInstance.getEmployeeId().toString();
        restTemplate.delete(employeeResourceUrl);

    }
}
