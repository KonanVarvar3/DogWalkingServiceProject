
let client;
let objectClient;

let newClient = {
    lastName:"",
    name:"",
    middleName:"",
    birthDate:"",
    phoneNumber:"",
    email:"",
    address:""
}

function clearClient() {

    let parent = document.getElementById("findTable");
    parent.innerText="";
}

function clearAllClient() {

    let parent = document.getElementById("clientTable");
    parent.innerText="";
}

function formFindClient(){
    let xhttp1 = new XMLHttpRequest();

    var str = document.getElementById("uniqueId").value;

    xhttp1.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            myFunction(this.responseText);
        }
    }

    xhttp1.open("GET",
        "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/client?uniqueId=" + str);
    xhttp1.send();

    function myFunction(data) {
        let array = JSON.parse(data, function(key, value) {
            if (key === 'birthDate') return new Date(value);
            return value;
        });

        //let array = JSON.parse(data);

        let parent = document.getElementById("findTable");
        parent.innerHTML = "";

        let table = document.createElement("table");

        createTitleTable(table);

        addDataFind(array,table);

        parent.append(table);
    }
}

function crt() {

    let out = document.getElementById("out");
    out.innerHTML="";

    newClient.lastName = document.getElementById("lastName").value;
    newClient.name = document.getElementById("name").value;
    newClient.middleName = document.getElementById("middleName").value;
    newClient.birthDate = document.getElementById("birthDate").value;
    newClient.phoneNumber = document.getElementById("phoneNumber").value;
    newClient.email = document.getElementById("email").value;
    newClient.address = document.getElementById("address").value;

    objectClient = JSON.stringify(newClient);
    //alert(objectClient);

    AJS.$.ajax({
        type: 'post',
        url: 'http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/createClient',
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: objectClient,
        success: function () {

            document.getElementById("lastName").value = "";
            document.getElementById("name").value = "";
            document.getElementById("middleName").value = "";
            document.getElementById("birthDate").value = "";
            document.getElementById("phoneNumber").value = "";
            document.getElementById("email").value = "";
            document.getElementById("address").value = "";

            document.getElementById("newClt").style.display = "block";

            setTimeout(function () {
                document.getElementById("newClt").style.display = "none";
            }, 3000);

            addFromBd();
        }
    });
}

function createTitleTable(table) {

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
    col8.innerHTML = "Address";
    row.append(col8);
}

function addDataFind(array,table) {

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

            case 4: key = array.birthDate.format("dd.mm.yyyy");
                break;

            case 5: key = array.phoneNumber;
                break;

            case 6: key = array.email;
                break;

            case 7: key = array.address;
                break;
        }

        addCol.innerHTML = key;
        addRow.append(addCol);
    }
}

function addDataTable(array,table) {
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

                case 7: key = array[i].address;
                    break;
            }

            addCol.innerHTML = key;
            addRow.append(addCol);
        }
    }
}

function addFromBd(){

    let xhttp2 = new XMLHttpRequest();

    xhttp2.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            myFunction(this.responseText);
        }
    }

    xhttp2.open("GET",
        "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/allClients");
    xhttp2.send();

    function myFunction(data) {

        let array = JSON.parse(data, function(key, value) {
            if (key === 'birthDate') return new Date(value);
            return value;
        });

        //let array = JSON.parse(data);

        let parent = document.getElementById("clientTable");
        parent.innerHTML = "";

        let table = document.createElement("table");

        createTitleTable(table);

        addDataTable(array,table);

        parent.append(table);
    }
}
