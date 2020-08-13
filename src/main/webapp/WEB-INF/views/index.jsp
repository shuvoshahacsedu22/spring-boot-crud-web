<%--
  Created by IntelliJ IDEA.
  User: tigerit
  Date: 3/18/20
  Time: 12:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>HRMS SYSTEM</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/myStyle.css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/js/lib/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/lib/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/lib/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/src/myFunctionsForSearch.js"></script>
    <script src="${pageContext.request.contextPath}/js/src/myFunctionsForDataTable.js"></script>
    <script src="${pageContext.request.contextPath}/js/src/myFunctionForFetchAndSendAPI.js"></script>
    <script>
        $(document).ready(function(){
            $("#myButtons_modalForNewEmployee").click(function(){
                //shows modal on button click and the backdrop options used is to make sure the modal is not closed when
                //clicked outside of the modal
                $("#myModals_modalForNewEmployee").modal({backdrop: "static"});
            });
        });
    </script>

</head>
<body>
<h1>Welcome to HRMS SYTEM</h1>
<!-- Trigger/Open The Modal -->

<!-- The Modal -->

<button type="button" class="btn btn-info btn-lg" id="myButtons_modalForNewEmployee">Open Modal</button>
<div>
<a href="/iTextTutorials"></a>
</div>
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
                    <input type="text" id="myModal_EmployeeName" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_EmployeeName" >Name</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_EmployeeEmail" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_EmployeeEmail">Email</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_EmployeeMobile" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_EmployeeMobile">Mobile</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_EmployeeDesignation" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_EmployeeDesignation">Designation</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_EmployeeDepartment" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_EmployeeDepartment">Department</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_EmployeeDepartmentId" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_EmployeeDepartmentId">Department Id</label>
                </div>
                <div class="md-form mb-5">
                    <i class="fas fa-user prefix grey-text"></i>
                    <input type="text" id="myModal_EmployeeDateOfJoining" class="form-control validate">
                    <label data-error="wrong" data-success="right" for="myModal_EmployeeDateOfJoining">Date of Joining</label>
                </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Click to Add</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>



        </div>
    </div>
</div>


</body>
</html>
