<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8" />
    <title>Spring Boot + JPA + Datatables</title>

    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>

    <script src="${pageContext.request.contextPath}/js/src/myFunctionForSearch.js"></script>

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
<script>
    $(document).ready(function() {
        $('#example').DataTable( {
            "sAjaxSource": "/restemployees",
            "sAjaxDataProp":"",
            "order":[[0,"asc"]],
            "aoColumns": [
                { "mData": "employeeId" },
                { "mData": "employeeName"},
                { "mData": "employeeDesignation" },
                { "mData": "employeeEmail" },
                { "mData": "employeeMobile" },
                { "mData": "departmentName" },
                { "mData": "departmentId" },
                { "mData": "joiningDate" }
            ]
        } );
    } );
</script>
<body>
<h1>Employees Table</h1>
<table id="example" class="display" style="width:100%">
    <!-- Header Table -->

    <thead>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Position</th>
        <th>Email</th>
        <th>Mobile</th>
        <th>Dept. Name</th>
        <th>Dept. Id</th>
        <th>Joining Date</th>
    </tr>
    </thead>
    <!-- Footer Table -->
    <tfoot>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Position</th>
        <th>Email</th>
        <th>Mobile</th>
        <th>Dept. Name</th>
        <th>Dept. Id</th>
        <th>Joining Date</th>
    </tr>
    </tfoot>
</table>
</body>
</html>