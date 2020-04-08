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
    <script src="${pageContext.request.contextPath}/js/src/ajaxEditDeleteUpdateEmployees.js"></script>


    <script>
        $(document).ready(function() {
            myFunctionFillData(document.getElementById('myTables_employeesTable'));
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

<%--Search, Registration, Edit, Delete buttons--%>
<div class="container" style="width: 100%">
    <div style="float: left">
    <span>
        <button type="button" id="myButtons_SearchEmployee" style="float: right" onclick="myFunctionForSearchOnButtonClick('myTables_employeesTable', ['employeeName','employeeId','departmentName','employeeEmail'] );"> Custom Search</button>
    </span>
    </div>

    <div style="float: right">
    <span>
            <button type="button" style="float: right" onclick="showDeleteModal();"> Delete Employee</button>
    </span>
    </div>


    <div style="float: right">
    <span>
            <button type="button" style="float: right" onclick="showUpdateModal();"> Edit/Update Employee</button>
    </span>
    </div>

    <div style="float: right;">
    <span>
            <button type="button" style="float: right" onclick="showAddModal();"> Add Employee</button>
    </span>
    </div>

</div>

<%--This modal pops up when clicked on add new employee button,and
 modals used here where text input is submitted via ajax--%>
<div class="modal fade" id="myModals_modalForNewEmployee" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title w-100 font-weight-bold">Add New Employee Form</h4>
            </div>
            <div class="modal-body mx-3">
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_newEmployeeName" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_newEmployeeName" >Name</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_newEmployeeEmail" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_newEmployeeEmail">Email</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_newEmployeeMobile" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_newEmployeeMobile">Mobile</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_newEmployeeDesignation" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_newEmployeeDesignation">Designation</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_newEmployeeDepartment" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_newEmployeeDepartment">Department</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_newEmployeeDepartmentId" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_newEmployeeDepartmentId">Department Id</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_newEmployeeDateOfJoining" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_newEmployeeDateOfJoining">Date of Joining</label>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="myButtons_addNewEmployeeSubmit" onclick="registerEmployee();">Click to Add</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>



        </div>
    </div>
</div>

<%--This modal pops up when clicked on add new employee button,and
 modals used here where text input is submitted via ajax--%>
<div class="modal fade" id="myModals_modalForUpdateEmployee" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title w-100 font-weight-bold">Update Employee Form</h4>
            </div>
            <div class="modal-body mx-3">
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_UpdateEmployeeId" class="form-control validate" onkeyup="fillInModalForm('update');">
                    <label data-error="wrong" data-success="right" for="myModal_UpdateEmployeeId" >Enter Id You Want to Update</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_UpdateEmployeeName" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_UpdateEmployeeName" >Name</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_UpdateEmployeeEmail" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_UpdateEmployeeEmail">Email</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_UpdateEmployeeMobile" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_UpdateEmployeeMobile">Mobile</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_UpdateEmployeeDesignation" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_UpdateEmployeeDesignation">Designation</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_UpdateEmployeeDepartment" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_UpdateEmployeeDepartment">Department</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_UpdateEmployeeDepartmentId" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_UpdateEmployeeDepartmentId">Department Id</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_UpdateEmployeeDateOfJoining" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_UpdateEmployeeDateOfJoining">Date of Joining</label>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="myButtons_updateNewEmployeeSubmit" onclick="updateEmployee();">Click to Update</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>



        </div>
    </div>
</div>

<%--This modal pops up when clicked on add new employee button,and
 modals used here where text input is submitted via ajax--%>
<div class="modal fade" id="myModals_modalForDeleteEmployee" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title w-100 font-weight-bold">Update Employee Form</h4>
            </div>
            <div class="modal-body mx-3">
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_DeleteEmployeeId" class="form-control validate" onkeyup="fillInModalForm('delete');">
                    <label data-error="wrong" data-success="right" for="myModal_DeleteEmployeeId" >Enter Id You Want to Update</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_DeleteEmployeeName" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_DeleteEmployeeName" >Name</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_DeleteEmployeeEmail" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_DeleteEmployeeEmail">Email</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_DeleteEmployeeMobile" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_DeleteEmployeeMobile">Mobile</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_DeleteEmployeeDesignation" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_DeleteEmployeeDesignation">Designation</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_DeleteEmployeeDepartment" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_DeleteEmployeeDepartment">Department</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_DeleteEmployeeDepartmentId" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_DeleteEmployeeDepartmentId">Department Id</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_DeleteEmployeeDateOfJoining" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_DeleteEmployeeDateOfJoining">Date of Joining</label>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="myButtons_DeleteEmployeeSubmit" onclick="deleteEmployee();">Click to Delete</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<%--Search Input Text Boxes--%>
<div class="container" style="width: 100%">
        <div >
            <div class="container" style="float: left">
                <label for="myTextBoxes_employeeName">Employee Name</label>
                <input align="left" type="text" id="myTextBoxes_employeeName"  placeholder="" onkeyup="myFunctionForSearchInInputTextBoxes('myTables_employeesTable','employeeName');">
            </div>
            <div class="container" style="float: left">
                <label for="myTextBoxes_employeeId">Employee ID</label>
                <input align="right" type="text" id="myTextBoxes_employeeId"  placeholder="" onkeyup="myFunctionForSearchInInputTextBoxes('myTables_employeesTable','employeeId');">
            </div >
            <div class="container" style="float: left">
                <label for="myTextBoxes_departmentName">Department/Team</label>
                <input type="text" id="myTextBoxes_departmentName"  placeholder="" onkeyup="myFunctionForSearchInInputTextBoxes('myTables_employeesTable','departmentName');">
            </div>
            <div class="container" style="float: left">
                <label for="myTextBoxes_employeeEmail">Email</label>
                <input type="text" id="myTextBoxes_employeeEmail"  placeholder="" onkeyup="myFunctionForSearchInInputTextBoxes('myTables_employeesTable','employeeEmail');">
            </div>
        </div>
</div>

<%-- Employee Table --%>
<div class="container" style="width: 100%">
    <table id="myTables_employeesTable" class="display" style="width:100%">
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
</div>

</body>
</html>
