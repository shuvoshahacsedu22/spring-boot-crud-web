function myFunctionFillData(myTables_tableId)
{
    $(myTables_tableId).DataTable( {
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
}
