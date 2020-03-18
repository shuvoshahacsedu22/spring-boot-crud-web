function myFunctionForSearchInInputTextBoxes(myTables_employeesTable_Id,myTables_employeesTable_columnName) {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myTextBoxes_"+myTables_employeesTable_columnName);
    filter = input.value.toUpperCase();
    table = document.getElementById(myTables_employeesTable_Id);
    tr = table.getElementsByTagName("tr");
    // Loop through all table rows, and hide those who don't match the search query

    for (i = 0; i < tr.length; i++) {
        //TODO removing raw code index in tr[i] array for employeeName which is 1 , [1]
        var id;
        {
            if(myTables_employeesTable_columnName==='employeeName') id=1;
            else if (myTables_employeesTable_columnName==='employeeId') id=0;
            else if(myTables_employeesTable_columnName==='departmentName') id=5;
            else if(myTables_employeesTable_columnName==='employeeEmail') id=3;

        }
        td = tr[i].getElementsByTagName("td")[id];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}

function myFunctionForSearchOnButtonClick(myTables_employeesTable_Id,myTables_employeesTable_columnName_Array){
    // Declare variables
    //TODO fixing the custom search which is not working properly...

    myFunctionForSearchInInputTextBoxes(myTables_employeesTable_Id,myTables_employeesTable_columnName_Array[0]);
    myFunctionForSearchInInputTextBoxes(myTables_employeesTable_Id,myTables_employeesTable_columnName_Array[1]);
    myFunctionForSearchInInputTextBoxes(myTables_employeesTable_Id,myTables_employeesTable_columnName_Array[2]);
    myFunctionForSearchInInputTextBoxes(myTables_employeesTable_Id,myTables_employeesTable_columnName_Array[3]);
}
