package com.tigerit.springbootcrudweb.model;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "department_table")
@EntityListeners(AuditingEntityListener.class)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long departmentId;
    @NotBlank
    @Column(nullable = false)
    private String departmentName;
    @NotBlank
    @Column(nullable = false)
    private Long numberOfEmployee;
    @NotBlank
    @Column(nullable = false)
    private String runningProjects;

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", numberOfEmployee=" + numberOfEmployee +
                ", runningProjects='" + runningProjects + '\'' +
                '}';
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Long getNumberOfEmployee() {
        return numberOfEmployee;
    }

    public void setNumberOfEmployee(Long numberOfEmployee) {
        this.numberOfEmployee = numberOfEmployee;
    }

    public String getRunningProjects() {
        return runningProjects;
    }

    public void setRunningProjects(String runningProjects) {
        this.runningProjects = runningProjects;
    }

    public Department() {
    }
    public Department(Long departmentId,String departmentName,Long numberOfEmployee,String runningProjects) {
        this.departmentId=departmentId;
        this.departmentName=departmentName;
        this.numberOfEmployee=numberOfEmployee;
        this.runningProjects=runningProjects;
    }
}