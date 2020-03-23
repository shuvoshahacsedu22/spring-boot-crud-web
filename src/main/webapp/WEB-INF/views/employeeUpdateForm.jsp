<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<%--
  Created by IntelliJ IDEA.
  User: tigerit
  Date: 3/10/20
  Time: 6:39 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE HTML>
<html xmlns:th="https://www.thymeleaf.org">
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
    <script src="${pageContext.request.contextPath}/js/src/myFunctionForFetchAndSendAPI.js"></script>

    <title>Update Form Submission</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<h1>
    Update Employee Information Table

</h1>

<div class="container">
<p id="myParagraph_errorComment0" style="color: red"></p>
</div>

<form:form  method="POST" action="/employeeUpdateForm" modelAttribute="employee">
    <form:label path="employeeId">Employee Id</form:label>
    <form:input id="myTextBoxes_employeeId" path="employeeId" placeholder="Employee Id" onkeyup="fetchData(0)"/>
<p>
    <form:label path="employeeName">Name</form:label>
    <form:input id="myForms_employeeUpdateForm_employeeName" path="employeeName" placeholder="Name"/>

    <form:label path="employeeEmail">Email</form:label>
    <form:input id="myForms_employeeUpdateForm_employeeEmail" path="employeeEmail" placeholder="Email"/>
</p>
<p>
    <form:label path="employeeMobile">Mobile</form:label>
    <form:input id="myForms_employeeUpdateForm_employeeMobile" path="employeeMobile" placeholder="Mobile"/>

    <form:label path="employeeDesignation">Designation</form:label>
    <form:input id="myForms_employeeUpdateForm_employeeDesignation" path="employeeDesignation" placeholder="Designation"/>
</p>

<p>
    <form:label path="departmentName">Department/Team</form:label>
    <form:input id="myForms_employeeUpdateForm_departmentName" path="departmentName" placeholder="Department/Team"/>

    <form:label path="departmentId">Department/Team Id</form:label>
    <form:input id="myForms_employeeUpdateForm_departmentId" path="departmentId" placeholder="Department/Team Id"/>
</p>

<p>
    <form:label path="joiningDate">Date of Joining</form:label>
        <form:input id="myForms_employeeUpdateForm_joiningDate" path="joiningDate" placeholder="Date of Joining"/>

    <input type="submit" value="Submit" />
    </form:form>

        <a href="/employees"><button >Return</button></a>
</body>
</html>