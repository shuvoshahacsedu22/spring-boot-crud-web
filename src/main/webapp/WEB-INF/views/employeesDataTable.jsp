<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Spring Boot + JPA + Datatable</title>
    <script src= "https://code.jquery.com/jquery-3.3.1.js"></script>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
</head>

<body>
<script>
    $(document).ready(function() {
        $('#example').DataTable( {
            "sAjaxSource": "http://localhost:8081/employees/",
            "columns": [
                { "data": "employeeId" },
                { "data": "employeeDesignation" },
                { "data": "employeeEmail" },
                { "data": "employeeMobile" },
                { "data": "departmentName" },
                { "data": "departmentId" },
                { "data": "joiningDate" }
            ]
        } );
    } );
</script>
<h1>Employees Table</h1><table id="example" class="display" style="width:100%">
    <thead>
    <tr>
        <th>Name</th>
        <th>Position</th>
        <th>Office</th>
        <th>Extn.</th>
        <th>Start date</th>
        <th>Salary</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th>Name</th>
        <th>Position</th>
        <th>Office</th>
        <th>Extn.</th>
        <th>Start date</th>
        <th>Salary</th>
    </tr>
    </tfoot>
</table>
</body>

</html>