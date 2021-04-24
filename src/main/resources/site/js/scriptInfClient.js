let objectClient;

let newClient = {
    lastName: "",
    name: "",
    middleName: "",
    birthDate: "",
    phoneNumber: "",
    email: "",
    address: ""
}

function clearClient() {
    let parent = document.getElementById("findTable");
    parent.innerText = "";
}

function clearAllClient() {
    let parent = document.getElementById("clientTable");
    parent.innerText = "";
}

function formFindClient() {
    let str = document.getElementById("uniqueId").value;

    AJS.$.ajax({
        type: 'get',
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/client?uniqueId=" + str,
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: 'json',

        success: function (data) {
            let parent = document.getElementById("findTable");
            parent.innerHTML = "";

            let table = document.createElement("table");

            createTitleTable(table);

            addDataFind(data, table);

            parent.append(table);
        }
    });
}

function checkValid() {
    let valid = document.getElementsByClassName("inputClient");
    let reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;

    let check = true;

    for (let i = 0; i < valid.length; i++) {
        if (valid[i].value === "") {
            switch (i) {
                case 0:
                    document.getElementById("inputLastName").innerHTML = "Fill Last name!";
                    break;
                case 1:
                    document.getElementById("inputName").innerHTML = "Fill Name!";
                    break;
                case 2:
                    document.getElementById("inputMiddleName").innerHTML = "Fill Middle name!";
                    break;
                case 3:
                    document.getElementById("inputBirthday").innerHTML = "Fill Birthday!";
                    break;
                case 4:
                    document.getElementById("inputPhone").innerHTML = "Fill Phone!";
                    break;
                case 5:
                    document.getElementById("inputEmail").innerHTML = "Fill Email!";
                    break;
                case 6:
                    document.getElementById("inputAddress").innerHTML = "Fill Address!";
                    break;
            }
            check = false;

        } else if (reg.test(valid[5].value) === false) {
            document.getElementById("inputEmail").innerHTML = "Incorrect Email!";
            check = false;
        }
    }
    return check;
}

function inputValueLastName() {
    document.getElementById("inputLastName").innerHTML = "";
}

function inputValueName() {
    document.getElementById("inputName").innerHTML = "";
}

function inputValueMiddleName() {
    document.getElementById("inputMiddleName").innerHTML = "";
}

function inputValueBirthday() {
    document.getElementById("inputBirthday").innerHTML = "";
}

function inputValuePhone() {
    document.getElementById("inputPhone").innerHTML = "";
}

function inputValueEmail() {
    document.getElementById("inputEmail").innerHTML = "";
}

function inputValueAddress() {
    document.getElementById("inputAddress").innerHTML = "";
}

$(function () {
    $('#date').daterangepicker({
        singleDatePicker: true,
        locale: {
            format: 'DD.MM.YYYY'
        }
    });
});

function crt() {
    if (checkValid() === true) {
        let out = document.getElementById("out");
        out.innerHTML = "";

        newClient.lastName = document.getElementById("lastName").value;
        newClient.name = document.getElementById("name").value;
        newClient.middleName = document.getElementById("middleName").value;
        newClient.birthDate = document.getElementById("birthDate").value;
        newClient.phoneNumber = document.getElementById("phoneNumber").value;
        newClient.email = document.getElementById("email").value;
        newClient.address = document.getElementById("address").value;

        objectClient = JSON.stringify(newClient);

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

function addDataFind(array, table) {
    let addRow = document.createElement('tr');
    table.append(addRow);

    for (let j = 0; j < 8; j++) {
        let addCol = document.createElement('td');

        let key = array.uniqueId;

        switch (j) {
            case 1:
                key = array.lastName;
                break;
            case 2:
                key = array.name;
                break;
            case 3:
                key = array.middleName;
                break;
            case 4:
                key = toDate(array.birthDate);
                break;
            case 5:
                key = array.phoneNumber;
                break;
            case 6:
                key = array.email;
                break;
            case 7:
                key = array.address;
                break;
        }
        addCol.innerHTML = key;
        addRow.append(addCol);
    }
}

function toDate(value) {
    let date = new Date(value);
    return date.toLocaleDateString();
}

function addDataTable(array, table) {
    for (let i = 0; i < array.length; i++) {

        let addRow = document.createElement('tr');
        table.append(addRow);

        for (let j = 0; j < 8; j++) {
            let addCol = document.createElement('td');

            let key = array[i].uniqueId;

            switch (j) {
                case 1:
                    key = array[i].lastName;
                    break;
                case 2:
                    key = array[i].name;
                    break;
                case 3:
                    key = array[i].middleName;
                    break;
                case 4:
                    key = toDate(array[i].birthDate);
                    break;
                case 5:
                    key = array[i].phoneNumber;
                    break;
                case 6:
                    key = array[i].email;
                    break;
                case 7:
                    key = array[i].address;
                    break;
            }
            addCol.innerHTML = key;
            addRow.append(addCol);
        }
    }
}

function addFromBd() {
    AJS.$.ajax({
        type: 'get',
        url: "http://localhost:8080/rest/dogWalkingRest/1.0/dogWalkingService/allClients",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        data: 'json',
        success: function (data) {

            let parent = document.getElementById("clientTable");
            parent.innerHTML = "";

            let table = document.createElement("table");

            createTitleTable(table);

            addDataTable(data, table);

            parent.append(table);
        }
    });
}
