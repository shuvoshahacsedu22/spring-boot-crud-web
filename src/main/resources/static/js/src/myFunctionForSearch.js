function myFunctionENAME() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInputENAME");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];//here [1] implies column/field index of respective table
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

function myFunctionEID() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInputEID");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0];
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

function myFunctionDEPT() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInputDEPT");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[4];
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

function myFunctionEMAIL() {
    // Declare variables
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("myInputEMAIL");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[3];
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

function myFunctionBUTTON(){
    // Declare variables
    var tdEID,tdENAME,tdEMAIL,tdDEPT,i,tr;
    var inputEID, filterEID, txtValueEID;
    var inputENAME, filterENAME, txtValueENAME;
    var inputEMAIL, filterEMAIL, txtValueEMAIL;
    var inputDEPT, filterDEPT, tableDEPT, txtValueDEPT;


    inputEID = document.getElementById("myInputEID");
    filterEID = inputEID.value.toUpperCase();

    inputEMAIL = document.getElementById("myInputEMAIL");
    filterEMAIL = inputEMAIL.value.toUpperCase();

    inputDEPT = document.getElementById("myInputDEPT");
    filterDEPT = inputDEPT.value.toUpperCase();

    inputENAME = document.getElementById("myInputENAME");
    filterENAME = inputENAME.value.toUpperCase();


    table = document.getElementById("myTable");



    tr = table.getElementsByTagName("tr");

    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
        tdEID = tr[i].getElementsByTagName("td")[0];
        tdENAME = tr[i].getElementsByTagName("td")[1];
        tdEMAIL = tr[i].getElementsByTagName("td")[3];
        tdDEPT = tr[i].getElementsByTagName("td")[4];
        if (tdEID ||tdENAME || tdEMAIL || tdDEPT) {
            if(tdEID)txtValueEID = tdEID.textContent || tdEID.innerText;
            if(tdDEPT)txtValueDEPT = tdDEPT.textContent || tdDEPT.innerText;
            if(tdEMAIL)txtValueEMAIL = tdEMAIL.textContent || tdEMAIL.innerText;
            if(tdENAME)txtValueENAME = tdENAME.textContent || tdENAME.innerText;
            var statusEID,statusENAME,statusEMAIL,statusDEPT;

            if(tdEID)
                statusEID= txtValueEID.toUpperCase().indexOf(filterEID) > -1;
            else statusEID=!tdEID;

            if(tdDEPT)
                statusDEPT= txtValueDEPT.toUpperCase().indexOf(filterDEPT) > -1;
            else statusDEPT=!tdDEPT;

            if(tdENAME)
                statusENAME= txtValueENAME.toUpperCase().indexOf(filterENAME) > -1;
            else statusENAME=!tdENAME;

            if(tdEMAIL)
                statusEMAIL= txtValueEMAIL.toUpperCase().indexOf(filterEMAIL) > -1;
            else statusEMAIL=!tdEMAIL;


            if (statusDEPT && statusEID && statusEMAIL && statusENAME) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
