function myFunctionFillData(myTables_tableId)//the 'myTables_tableId' should have columns accordingly
{
    var table=$(myTables_tableId).DataTable( {
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
    setInterval( function () {
        table.ajax.reload();
    }, 3000 );

}
