<!DOCTYPE HTML>
<html xmlns:th="https://www.thymeleaf.org">
<head><link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/common.css" rel="stylesheet">
    <script src="/js/src/myFunctionForSearch.js"></script>

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
    <title>New Employee Information</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<h1 >Newly Added Employee Information</h1>
<p >${employee.employeeName} </p>
<p >${employee.employeeDesignation} </p>
<p >${employee.departmentName} </p>
<p >${employee.departmentId} </p>
<p >${employee.employeeEmail} </p>
<p >${employee.employeeMobile} </p>
<p >${employee.joiningDate} </p>


<div>
    <span>
        <a href="/employeeRegistrationForm">
            <button type="button" id="buttonAddNewEmployee" style="float: left"> Add Another Employee</button>
        </a>
    </span>
    <a href="/employees">
        <button type="button" id="returnToEmployees" style="float: left"> Return </button>
    </a>
</div></body>
</html>
