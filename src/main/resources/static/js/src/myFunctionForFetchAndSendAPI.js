function fetchData(stat) {

    var input, filter;
    input = document.getElementById("myTextBoxes_employeeId");
    id = input.value.toUpperCase();
    if(id==='') id='0';
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if(id==='0'){
            document.getElementById('myForms_employeeUpdateForm_employeeName').setAttribute('value','');
            //document.getElementById('myForms_employeeUpdateForm_employeeName').setAttribute('placeholder',employee.employeeName);
            document.getElementById('myForms_employeeUpdateForm_employeeEmail').setAttribute('value','');
            document.getElementById('myForms_employeeUpdateForm_departmentName').setAttribute('value','');
            document.getElementById('myForms_employeeUpdateForm_departmentId').setAttribute('value','');
            document.getElementById('myForms_employeeUpdateForm_joiningDate').setAttribute('value','');
            document.getElementById('myForms_employeeUpdateForm_employeeMobile').setAttribute('value','');
            document.getElementById('myForms_employeeUpdateForm_employeeDesignation').setAttribute('value','');


            document.getElementById("myParagraph_errorComment").innerHTML = '';
            document.getElementById('myForms_employeeUpdateForm_employeeName').setAttribute('placeholder','Name');
            document.getElementById('myForms_employeeUpdateForm_employeeEmail').setAttribute('placeholder','Email');
            document.getElementById('myForms_employeeUpdateForm_departmentName').setAttribute('placeholder','Department/Team');
            document.getElementById('myForms_employeeUpdateForm_departmentId').setAttribute('placeholder','Department/Team Id');
            document.getElementById('myForms_employeeUpdateForm_joiningDate').setAttribute('placeholder','Date of Joining');
            document.getElementById('myForms_employeeUpdateForm_employeeMobile').setAttribute('placeholder','Mobile');
            document.getElementById('myForms_employeeUpdateForm_employeeDesignation').setAttribute('placeholder','employeeDesignation');
        }
        else if (this.readyState == 4 && this.status == 200) {
            var obj=JSON.parse(this.responseText);
            var employee=obj[0];

            if(stat==0)
                document.getElementById("myParagraph_errorComment0").innerHTML ='Employee with id'+id+'exist. Update in the following boxes.';
            else
                document.getElementById("myParagraph_errorComment1").innerHTML ='Employee with id'+id+'exist. Click Submit to delete.';

            document.getElementById('myForms_employeeUpdateForm_employeeName').setAttribute('value',employee.employeeName);
            //document.getElementById('myForms_employeeUpdateForm_employeeName').setAttribute('placeholder',employee.employeeName);
            document.getElementById('myForms_employeeUpdateForm_employeeEmail').setAttribute('value',employee.employeeEmail);
            document.getElementById('myForms_employeeUpdateForm_departmentName').setAttribute('value',employee.departmentName);
            document.getElementById('myForms_employeeUpdateForm_departmentId').setAttribute('value',employee.departmentId);
            document.getElementById('myForms_employeeUpdateForm_joiningDate').setAttribute('value',employee.joiningDate);
            document.getElementById('myForms_employeeUpdateForm_employeeMobile').setAttribute('value',employee.employeeMobile);
            document.getElementById('myForms_employeeUpdateForm_employeeDesignation').setAttribute('value',employee.employeeDesignation);

        }
        else{
            document.getElementById('myForms_employeeUpdateForm_employeeName').setAttribute('value','');
            //document.getElementById('myForms_employeeUpdateForm_employeeName').setAttribute('placeholder',employee.employeeName);
            document.getElementById('myForms_employeeUpdateForm_employeeEmail').setAttribute('value','');
            document.getElementById('myForms_employeeUpdateForm_departmentName').setAttribute('value','');
            document.getElementById('myForms_employeeUpdateForm_departmentId').setAttribute('value','');
            document.getElementById('myForms_employeeUpdateForm_joiningDate').setAttribute('value','');
            document.getElementById('myForms_employeeUpdateForm_employeeMobile').setAttribute('value','');
            document.getElementById('myForms_employeeUpdateForm_employeeDesignation').setAttribute('value','');

            document.getElementById("myParagraph_errorComment").innerHTML = 'Employee with id'+id+'does not exist';
            document.getElementById('myForms_employeeUpdateForm_employeeName').setAttribute('placeholder','Name');
            document.getElementById('myForms_employeeUpdateForm_employeeEmail').setAttribute('placeholder','Email');
            document.getElementById('myForms_employeeUpdateForm_departmentName').setAttribute('placeholder','Department/Team');
            document.getElementById('myForms_employeeUpdateForm_departmentId').setAttribute('placeholder','Department/Team Id');
            document.getElementById('myForms_employeeUpdateForm_joiningDate').setAttribute('placeholder','Date of Joining');
            document.getElementById('myForms_employeeUpdateForm_employeeMobile').setAttribute('placeholder','Mobile');
            document.getElementById('myForms_employeeUpdateForm_employeeDesignation').setAttribute('placeholder','employeeDesignation');

        }
    };
    xhttp.open("GET", "/restemployees/"+id, true);
    xhttp.send();
}