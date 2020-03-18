<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<!DOCTYPE html>
<html>
<head>

    <link href="${pageContext.request.contextPath}/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/myStyle.css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/js/lib/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/lib/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/lib/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/src/myFunctionsForSearch.js"></script>
    <script src="${pageContext.request.contextPath}/js/src/myFunctionsForDataTable.js"></script>


    <script>
        $(document).ready(function() {
            myFunctionFillData(document.getElementById('employeesTable'));
        } );
    </script>

</head>

<body>
<h1>Employees Table</h1>

<%--Header Title--%>
<div>
    <span style="background-color: #dddddd">
            <h2 style="alignment: center">Employee Information Table(With Data Table/Ajax Data Source)</h2>
    </span>
</div>

<%--Search Button--%>

<div>
    <span>
        <button type="button" id="myButton" style="float: right" onclick="myFunctionForSearchOnButtonClick('employeesTable', ['employeeName','employeeId','departmentName','employeeEmail'] );"> Custom Search</button>
    </span>
</div>

<%--Search Boxes--%>
<div class="container">
    <div class="center">
        <div class="row">
            <div>
                <label for="myTextBoxes_employeeName">Employee Name</label>
                <input align="left" type="text" id="myTextBoxes_employeeName"  placeholder="" onkeyup="myFunctionForSearchInInputTextBoxes('employeesTable','employeeName');">

                <label for="myTextBoxes_employeeId">Employee ID</label>
                <input align="right" type="text" id="myTextBoxes_employeeId"  placeholder="" onkeyup="myFunctionForSearchInInputTextBoxes('employeesTable','employeeId');">
            </div >

            <div >
                <label for="myTextBoxes_departmentName">Department/Team</label>
                <input type="text" id="myTextBoxes_departmentName"  placeholder="" onkeyup="myFunctionForSearchInInputTextBoxes('employeesTable','departmentName');">

                <label for="myTextBoxes_employeeEmail">Email</label>
                <input type="text" id="myTextBoxes_employeeEmail"  placeholder="" onkeyup="myFunctionForSearchInInputTextBoxes('employeesTable','employeeEmail');">
            </div>
        </div>
    </div>
</div>

    <table id="employeesTable" class="display" style="width:100%">
        <!-- Header Table -->
        <thead>
        <tr class="header">
            <th>Id</th>
            <th>Name</th>
            <th>Title</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Team Name</th>
            <th>Team Id</th>
            <th>Joined Date</th>

        </tr>
        </thead>

<%--   Data table using JSTL tag for accessing model/docuement object/element: --%>
<%--
        <c:forEach items="${employees}" var="employee">
            <tr>
                <td>${employee.employeeId}</td>
                <td>${employee.employeeName}</td>
                <td>${employee.employeeDesignation}</td>
                <td>${employee.employeeEmail}</td>
                <td>${employee.departmentName}</td>
                <td>${employee.departmentId}</td>
                <td>${employee.joiningDate}</td>
                <td>${employee.employeeMobile}</td>
                <td>${"null"}</td>
            </tr>

        </c:forEach>
--%>


        <!-- Footer Table -->
        <tfoot>
        <tr class="header">
            <th>Id</th>
            <th>Name</th>
            <th>Title</th>
            <th>Email</th>
            <th>Mobile</th>
            <th>Team Name</th>
            <th>Team Id</th>
            <th>Joined Date</th>
        </tr>
        </tfoot>
    </table>

<%-- Registration button--%>
<div>
    <span>
        <a href="/employeeRegistrationForm">
            <button type="button" id="buttonAddNewEmployee" style="float: right" > Add Employee</button>
        </a>
    </span>
</div>
</body>
</html>
