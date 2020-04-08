function showAddModal(){
            //shows modal on button click and the backdrop options used is to make sure the modal is not closed when
            //clicked outside of the modal
            $("#myModals_modalForNewEmployee").modal({backdrop: "static"});
        }
function showUpdateModal(){
    //shows modal on button click and the backdrop options used is to make sure the modal is not closed when
    //clicked outside of the modal
    $("#myModals_modalForUpdateEmployee").modal({backdrop: "static"});
    //use of jsonp enables one to call cross site api......
}
function showDeleteModal(){
    //shows modal on button click and the backdrop options used is to make sure the modal is not closed when
    //clicked outside of the modal
    $("#myModals_modalForDeleteEmployee").modal({backdrop: "static"});
}
//here the parameter clarifies if the method is called from update modal or delete modal
function fillInModalForm(deleteOrUpdate) {
    if(deleteOrUpdate=='update'){


        var txt = $("#myModal_UpdateEmployeeId").val().toString();
        //TODO if employee do not exist with id 'txt' then what should be done...
        // the /restemployees/{id} url returns list of employees list<employees> lst;

        $.ajax({
            type: "GET",
            dataType: "json",
            url: "/restemployees/"+txt,
            success: function (data) {
                $("#myModal_UpdateEmployeeName").attr('value',data[0].employeeName);
                $("#myModal_UpdateEmployeeEmail").attr('value',data[0].employeeEmail);
                $("#myModal_UpdateEmployeeMobile").attr('value',data[0].employeeMobile);
                $("#myModal_UpdateEmployeeDepartment").attr('value',data[0].departmentName);
                $("#myModal_UpdateEmployeeDepartmentId").attr('value',data[0].departmentId);
                $("#myModal_UpdateEmployeeDateOfJoining").attr('value',data[0].joiningDate);
                $("#myModal_UpdateEmployeeDesignation").attr('value',data[0].employeeDesignation);

                //console.log(data[0].employeeMobile);
            },
            error: function (e) {
    //            $("#myModal_UpdateEmployeeId").attr('value','The Employee With This ID does not exist');
                $("#myModal_UpdateEmployeeName").attr('value','The Employee With This ID does not exist');

                $("#myModal_UpdateEmployeeEmail").attr('value','  ');
                $("#myModal_UpdateEmployeeMobile").attr('value','  ');
                $("#myModal_UpdateEmployeeDepartment").attr('value','  ');
                $("#myModal_UpdateEmployeeDepartmentId").attr('value','   ');
                $("#myModal_UpdateEmployeeDateOfJoining").attr('value','  ');
                $("#myModal_UpdateEmployeeDesignation").attr('value','  ');

              //  console.log("ERROR : ", "id does not exit");

            }
        });
    }
    else{
        var txt = $("#myModal_DeleteEmployeeId").val().toString();
        //TODO if employee do not exist with id 'txt' then what should be done...
        // the /restemployees/{id} url returns list of employees list<employees> lst;

        $.ajax({
            type: "GET",
            dataType: "json",
            url: "/restemployees/"+txt,
            success: function (data) {
                $("#myModal_DeleteEmployeeName").attr('value',data[0].employeeName);
                $("#myModal_DeleteEmployeeEmail").attr('value',data[0].employeeEmail);
                $("#myModal_DeleteEmployeeMobile").attr('value',data[0].employeeMobile);
                $("#myModal_DeleteEmployeeDepartment").attr('value',data[0].departmentName);
                $("#myModal_DeleteEmployeeDepartmentId").attr('value',data[0].departmentId);
                $("#myModal_DeleteEmployeeDateOfJoining").attr('value',data[0].joiningDate);
                $("#myModal_DeleteEmployeeDesignation").attr('value',data[0].employeeDesignation);

                //console.log(data[0].employeeMobile);
            },
            error: function (e) {
                //            $("#myModal_UpdateEmployeeId").attr('value','The Employee With This ID does not exist');
                $("#myModal_DeleteEmployeeName").attr('value','The Employee With This ID does not exist');
                $("#myModal_DeleteEmployeeEmail").attr('value','  ');
                $("#myModal_DeleteEmployeeMobile").attr('value','  ');
                $("#myModal_DeleteEmployeeDepartment").attr('value','  ');
                $("#myModal_DeleteEmployeeDepartmentId").attr('value','   ');
                $("#myModal_DeleteEmployeeDateOfJoining").attr('value','  ');
                $("#myModal_DeleteEmployeeDesignation").attr('value','  ');

            //    console.log("ERROR : ", "id does not exit");

            }
        });

    }
}

function registerEmployee(){
        //click button to add new employee using ajax post
            // array which taking input of textbox of modals
            var newEmployeeDataArray = {};
            //TODO to check if any textbox is empty i.e if any variable is null
            newEmployeeDataArray["employeeId"]=null;
            newEmployeeDataArray["employeeName"] = $("#myModal_newEmployeeName").val();
            newEmployeeDataArray["employeeDesignation"] = $("#myModal_newEmployeeDesignation").val();
            newEmployeeDataArray["employeeEmail"] = $("#myModal_newEmployeeEmail").val();
            newEmployeeDataArray["employeeMobile"] = $("#myModal_newEmployeeMobile").val();
            newEmployeeDataArray["departmentName"] = $("#myModal_newEmployeeDepartment").val();
            newEmployeeDataArray["departmentId"] = $("#myModal_newEmployeeDepartmentId").val();
            newEmployeeDataArray["joiningDate"] = $("#myModal_newEmployeeDateOfJoining").val();
            //ajax post object examples
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/restemployees",
                data: JSON.stringify(newEmployeeDataArray), // convert array to JSON
                dataType: 'json',
                cache: false,
                timeout: 100000,
                success: function (data) {
                    console.log("SUCCESS : ", data.employeeName);
                },
                error: function (e) {
                    console.log("ERROR : ", e);
                }
            });
            //TODO the data table should reload after insert , delete or update
        //$('#myTables_employeesTable').DataTable().ajax.reload();

    //to remove text box value
    $("#myModal_newEmployeeName").attr('value',' ');
    $("#myModal_newEmployeeEmail").attr('value',' ');
    $("#myModal_newEmployeeMobile").attr('value',' ');
    $("#myModal_newEmployeeDepartment").attr('value',' ');
    $("#myModal_newEmployeeDepartmentId").attr('value',' ');
    $("#myModal_newEmployeeDateOfJoining").attr('value',' ');
    $("#myModal_newEmployeeDesignation").attr('value',' ');
        //console.log('i am done');
    //myFunctionFillData(document.getElementById());
            //$('#employeesTable').DataTable().ajax.reload();

}
function updateEmployee() {
    //click button to add new employee using ajax post
    // array which taking input of textbox of modals
    var updateEmployeeDataArray = {};
    //TODO to check if any textbox is empty i.e if any variable is null

    updateEmployeeDataArray["employeeId"] = $("#myModal_UpdateEmployeeId").val();
    updateEmployeeDataArray["employeeName"] = $("#myModal_UpdateEmployeeName").val();
    updateEmployeeDataArray["employeeDesignation"] = $("#myModal_UpdateEmployeeDesignation").val();
    updateEmployeeDataArray["employeeEmail"] = $("#myModal_UpdateEmployeeEmail").val();
    updateEmployeeDataArray["employeeMobile"] = $("#myModal_UpdateEmployeeMobile").val();
    updateEmployeeDataArray["departmentName"] = $("#myModal_UpdateEmployeeDepartment").val();
    updateEmployeeDataArray["departmentId"] = $("#myModal_UpdateEmployeeDepartmentId").val();
    updateEmployeeDataArray["joiningDate"] = $("#myModal_UpdateEmployeeDateOfJoining").val();
    var txt = $("#myModal_UpdateEmployeeId").val().toString();
    var urlResource = "/restemployees"
    $.ajax({
        type: "PUT",
        contentType: "application/json",
        url: urlResource,
        data: JSON.stringify(updateEmployeeDataArray), // convert array to JSON
        dataType: 'json',
        cache: false,
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS : ", data.employeeName);
        },
        error: function (e) {
            console.log("ERROR : ", e);

        }
    });
    //to remove text box value
    $("#myModal_UpdateEmployeeId").attr('value',' ');
    $("#myModal_UpdateEmployeeName").attr('value',' ');
    $("#myModal_UpdateEmployeeEmail").attr('value',' ');
    $("#myModal_UpdateEmployeeMobile").attr('value',' ');
    $("#myModal_UpdateEmployeeDepartment").attr('value',' ');
    $("#myModal_UpdateEmployeeDepartmentId").attr('value',' ');
    $("#myModal_UpdateEmployeeDateOfJoining").attr('value',' ');
    $("#myModal_UpdateEmployeeDesignation").attr('value',' ');
    //console.log('i am done');
    //myFunctionFillData(document.getElementById());
    //$('#employeesTable').DataTable().ajax.reload();
}
function deleteEmployee() {
    var deleteEmployeeDataArray = {};
    //TODO to check if any textbox is empty i.e if any variable is null
    deleteEmployeeDataArray["employeeId"] = $("#myModal_DeleteEmployeeId").val();
    deleteEmployeeDataArray["employeeName"] = $("#myModal_DeleteEmployeeName").val();
    deleteEmployeeDataArray["employeeDesignation"] = $("#myModal_DeleteEmployeeDesignation").val();
    deleteEmployeeDataArray["employeeEmail"] = $("#myModal_DeleteEmployeeEmail").val();
    deleteEmployeeDataArray["employeeMobile"] = $("#myModal_DeleteEmployeeMobile").val();
    deleteEmployeeDataArray["departmentName"] = $("#myModal_DeleteEmployeeDepartment").val();
    deleteEmployeeDataArray["departmentId"] = $("#myModal_DeleteEmployeeDepartmentId").val();
    deleteEmployeeDataArray["joiningDate"] = $("#myModal_DeleteEmployeeDateOfJoining").val();
    //ajax post object examples
    $.ajax({
        type: "DELETE",
        contentType: "application/json",
        url: "/restemployees",
        data: JSON.stringify(deleteEmployeeDataArray), // convert array to JSON
        dataType: 'json',
        cache: false,
        timeout: 100000,
        success: function (data) {
            console.log("SUCCESS : ", data.employeeName);
        },
        error: function (e) {
            console.log("ERROR : ", e);
        }
    });
    //TODO the data table should reload after insert , delete or update
    //$('#myTables_employeesTable').DataTable().ajax.reload();

    //to remove text box value
    $("#myModal_DeleteEmployeeId").attr('value',' ');
    $("#myModal_DeleteEmployeeName").attr('value',' ');
    $("#myModal_DeleteEmployeeEmail").attr('value',' ');
    $("#myModal_DeleteEmployeeMobile").attr('value',' ');
    $("#myModal_DeleteEmployeeDepartment").attr('value',' ');
    $("#myModal_DeleteEmployeeDepartmentId").attr('value',' ');
    $("#myModal_DeleteEmployeeDateOfJoining").attr('value',' ');
    $("#myModal_DeleteEmployeeDesignation").attr('value',' ');
    //console.log('i am done');
    //myFunctionFillData(document.getElementById());
    //$('#employeesTable').DataTable().ajax.reload();

}