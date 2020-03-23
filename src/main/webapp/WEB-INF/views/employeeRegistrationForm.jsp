<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: tigerit
  Date: 3/10/20
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html xmlns:th="https://www.thymeleaf.org">
<head>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
    <script src="/static/js/src/myFunctionsForSearch.js"></script>

    <style>
        table {
            border-collapse: collapse;
            border: 1px grey;
            width: 100%;
            align: center;

        }

        td, th {
            border: 0.5px solid grey;
            align: center;
        }
        th{
            border: 1px solid black;
        }
        td{
            height: 5px;
        }
        tr:nth-child(even) {
            background-color: #ffffff;
            height: 5px;
        }
        font{
            font-family: arial;
        }
        button{
            background-color: #2e6da4;
            color: #bce8f1;
        }
    </style>
    <title>Form Submission</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<h1>
   New Employee Information Table
</h1>

<form:form method="POST" action="/employeeRegistrationForm" modelAttribute="employee">
    <p>
        <form:label path="employeeName">Name</form:label>
        <form:input path="employeeName" />

        <form:label path="employeeEmail">Email</form:label>
        <form:input path="employeeEmail" />
    </p>
    <p>
        <form:label path="employeeMobile">Mobile</form:label>
        <form:input path="employeeMobile" />

        <form:label path="employeeDesignation">Designation</form:label>
        <form:input path="employeeDesignation" />
    </p>

    <p>
        <form:label path="departmentName">Department/Team</form:label>
        <form:input path="departmentName" />

        <form:label path="departmentId">Department/Team Id</form:label>
        <form:input path="departmentId" />
    </p>

    <p>
        <form:label path="joiningDate">Date of Joining</form:label>
        <form:input path="joiningDate" />

    <input type="submit" value="Submit" />
</form:form>

<a href="/employees"><button >Return</button></a>
</body>
</html>