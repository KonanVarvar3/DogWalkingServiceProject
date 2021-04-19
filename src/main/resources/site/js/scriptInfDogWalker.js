
let objectDogWalker;

let newDogWalker = {
    lastName:"",
    name:"",
    middleName:"",
    birthDate:"",
    phoneNumber:"",
    email:""
    // status:""
}

function clearDogWalker() {

    let parent = document.getElementById("findTableDW");
    parent.innerText="";
}

function clearAllDogWalkers() {

    let parent = document.getElementById("dogWalkerTable");
    parent.innerText="";
}

function formFindDogWalker(){
    let xhttp1 = new XMLHttpRequest();

    var str = document.getElementById("uniqueIdDW").value;

    xhttp1.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            myFunctionDW(this.responseText);
        }
    }

    xhttp1.open("GET",
        "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/dogWalker?uniqueId=" + str);
    xhttp1.send();

    function myFunctionDW(data) {
        let array = JSON.parse(data, function(key, value) {
            if (key === 'birthDate') return new Date(value);
            return value;
        });

        //let array = JSON.parse(data);

        let parent = document.getElementById("findTableDW");
        parent.innerHTML = "";

        let table = document.createElement("table");

        createTitleTableDW(table);

        addDataFindDW(array,table);

        parent.append(table);
    }
}

function crtDW() {

    let out = document.getElementById("outDW");
    out.innerHTML="";

    newDogWalker.lastName = document.getElementById("lastNameDW").value;
    newDogWalker.name = document.getElementById("nameDW").value;
    newDogWalker.middleName = document.getElementById("middleNameDW").value;
    newDogWalker.birthDate = document.getElementById("birthDateDW").value;
    newDogWalker.phoneNumber = document.getElementById("phoneNumberDW").value;
    newDogWalker.email = document.getElementById("emailDW").value;
    // newClient.status = document.getElementById("statusDW").value;

    objectDogWalker = JSON.stringify(newDogWalker);

    AJS.$.ajax({
        type: 'post',
        url: 'http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/createDogWalker',
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: objectDogWalker,
        success: function () {

            document.getElementById("lastNameDW").value = "";
            document.getElementById("nameDW").value = "";
            document.getElementById("middleNameDW").value = "";
            document.getElementById("birthDateDW").value = "";
            document.getElementById("phoneNumberDW").value = "";
            document.getElementById("emailDW").value = "";
            // document.getElementById("statusDW").value = "";

            document.getElementById("newDgWlkr").style.display = "block";

            setTimeout(function () {
                document.getElementById("newDgWlkr").style.display = "none";
            }, 3000);

            addFromBdDW();
        }
    });
}

function createTitleTableDW(table) {

    let row = document.createElement("tr");
    table.append(row);

    let col1 = document.createElement("td");
    col1.innerHTML = "Unique ID";
    row.append(col1);

    let col2 = document.createElement("td");
    col2.innerHTML = "Last name";
    row.append(col2);

    let col3 = document.createElement("td");
    col3.innerHTML = "Name";
    row.append(col3);

    let col4 = document.createElement("td");
    col4.innerHTML = "Middle name";
    row.append(col4);

    let col5 = document.createElement("td");
    col5.innerHTML = "Birth date";
    row.append(col5);

    let col6 = document.createElement("td");
    col6.innerHTML = "Phone number";
    row.append(col6);

    let col7 = document.createElement("td");
    col7.innerHTML = "Email";
    row.append(col7);

    let col8 = document.createElement("td");
    col8.innerHTML = "Status";
    row.append(col8);
}

function addDataFindDW(array,table) {

    let addRow = document.createElement('tr');
    table.append(addRow);

    for (let j = 0; j < 8; j++) {

        let addCol = document.createElement('td');

        let key = array.uniqueId;

        switch (j) {
            case 1: key = array.lastName;
                break;

            case 2: key = array.name;
                break;

            case 3: key = array.middleName;
                break;

            case 4: key = array.birthDate;
                break;

            case 5: key = array.phoneNumber;
                break;

            case 6: key = array.email;
                break;

            case 7: key = array.status;
                break;
        }

        addCol.innerHTML = key;
        addRow.append(addCol);
    }
}

function addDataTableDW(array,table) {
    for (let i = 0; i < array.length; i++) {

        let addRow = document.createElement('tr');
        table.append(addRow);

        for (let j = 0; j < 8; j++) {

            let addCol = document.createElement('td');

            let key = array[i].uniqueId;

            switch (j) {
                case 1: key = array[i].lastName;
                    break;

                case 2: key = array[i].name;
                    break;

                case 3: key = array[i].middleName;
                    break;

                case 4: key = array[i].birthDate;
                    break;

                case 5: key = array[i].phoneNumber;
                    break;

                case 6: key = array[i].email;
                    break;

                case 7: key = array[i].status;
                    break;
            }

            addCol.innerHTML = key;
            addRow.append(addCol);
        }
    }
}

function addFromBdDW(){

    let xhttp2 = new XMLHttpRequest();

    xhttp2.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            myFunctionDW(this.responseText);
        }
    }

    xhttp2.open("GET",
        "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/allDogWalkers");
    xhttp2.send();

    function myFunctionDW(data) {

        let array = JSON.parse(data, function(key, value) {
            if (key === 'birthDate') return new Date(value);
            return value;
        });

        //let array = JSON.parse(data);

        let parent = document.getElementById("dogWalkerTable");
        parent.innerHTML = "";

        let table = document.createElement("table");

        createTitleTableDW(table);

        addDataTableDW(array,table);

        parent.append(table);
    }
}
