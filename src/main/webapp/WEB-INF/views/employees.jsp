<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html>
<head>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
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
</head>
<body>

<div>
    <span style="background-color: #dddddd">
<h2 style="alignment: center">Employee Information Table</h2>
        </span>
</div>

<div>
    <span>
        <button type="button" id="buttonSearch" style="float: right" onclick="myFunctionBUTTON()"> Search</button>
    </span>
</div>
<div class="container">
    <div class="center">
        <div class="row">
            <div>
                <label for="myInputENAME">Employee Name</label>
                <input align="left" type="text" id="myInputENAME"  placeholder="" onkeyup="myFunctionENAME()">

                <label for="myInputEID">Employee ID</label>
                <input align="right" type="text" id="myInputEID"  placeholder=""onkeyup="myFunctionEID()">
            </div >
            <div >
                <label for="myInputDEPT">Department/Team</label>
                <input type="text" id="myInputDEPT"  placeholder=""onkeyup="myFunctionDEPT()">

                <label for="myInputEMAIL">Email</label>
                <input type="text" id="myInputEMAIL"  placeholder=""onkeyup="myFunctionEMAIL()">
            </div>
        </div>
    </div>
</div>
    <table id="myTable">
        <tr class="header">
            <th style="width:5%;">Id</th>
            <th style="width:15%;">Name</th>
            <th style="width:15%;">Title</th>
            <th style="width:10%;">Email</th>
            <th style="width:25%;">Team Name</th>
            <th style="width:10%;">Joined Date</th>
            <th style="width:10%;">Mobile</th>
            <th style="width:10%;">Action</th>
        </tr>
        <c:forEach items="${employees}" var="employee">
            <tr>
                <td>${employee.employeeId}</td>
                <td>${employee.employeeName}</td>
                <td>${employee.employeeDesignation}</td>
                <td>${employee.employeeEmail}</td>
                <td>${employee.departmentName}</td>
                <td>${employee.joiningDate}</td>
                <td>${employee.employeeMobile}</td>
                <td>${"null"}</td>
            </tr>

        </c:forEach>
    </table>
<div>
    <span>
        <button type="button" id="buttonAdd" style="float: right" onclick="myFunctionBUTTON()"> Add Employee</button>
    </span>
</div>
</body>
</html>
